package mattapps.jiujitsu.timer.ui.navigation

import android.app.Activity
import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import mattapps.jiujitsu.timer.R
import mattapps.jiujitsu.timer.ui.navigation.Destinations.SCOREKEEPER_ROUTE
import mattapps.jiujitsu.timer.ui.navigation.Destinations.TIMERSET_ROUTE
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.ScoringEvent
import mattapps.jiujitsu.timer.ui.model.ScorekeeperViewModel
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetEvent

object Destinations {
    const val SCOREKEEPER_ROUTE = "scorekeeper"
    const val TIMERSET_ROUTE = "timerset"
}

data class standardAnimation(
    val route: String,
    val directionIn: AnimatedContentTransitionScope.SlideDirection,
    val directionOut: AnimatedContentTransitionScope.SlideDirection,
    val animationSpec: FiniteAnimationSpec<IntOffset> = tween(
        easing = FastOutSlowInEasing,
        durationMillis = 700,
        delayMillis = 100
    ),
    val exitAnimation: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = {
        when (initialState.destination.route) {
            route ->
                slideOutOfContainer(
                    towards = directionOut,
                    animationSpec = animationSpec
                )
            else -> null
        }},
    val enterAnimation: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?  = {
            when (initialState.destination.route) {
                route ->
                    slideIntoContainer(
                        towards = directionIn,
                        animationSpec = animationSpec
                    )
                else -> null
            }
    }
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TimerNavHost(
    scorekeeperViewModel: ScorekeeperViewModel = viewModel(),
    navController: NavHostController = rememberAnimatedNavController(),
) {

    val timeState by scorekeeperViewModel.timeState.collectAsState()
    val scorekeeperUiState by scorekeeperViewModel.scorekeeperUiState.collectAsState()

    val mins = scorekeeperViewModel.mins.collectAsState()
    val secs = scorekeeperViewModel.secs.collectAsState()
    val startStopText = scorekeeperViewModel.startStopText.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current
    val currentDestination = remember { mutableStateOf(SCOREKEEPER_ROUTE) }

    var mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.alarmtone)
    }

    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_DESTROY -> mediaPlayer.release()
                else -> Unit
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    DisposableEffect(Unit) {
        scorekeeperViewModel.mediaPlayerController = object : ScorekeeperViewModel.MediaPlayerController {
            override fun start() {
                mediaPlayer.start()
            }

            override fun stop() {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(context, R.raw.alarmtone)
            }

            override fun reset() {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
                mediaPlayer.reset()
                mediaPlayer = MediaPlayer.create(context, R.raw.alarmtone)
            }
        }

        onDispose {
            scorekeeperViewModel.mediaPlayerController = null
        }
    }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentDestination.value = destination.route ?: ""
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    BackHandler(onBack = {
        if (currentDestination.value == SCOREKEEPER_ROUTE) {
            (context as Activity).finish()
        } else {
            navController.popBackStack()
        }
    })

    val timerAnimation = standardAnimation(TIMERSET_ROUTE, directionIn = Right, directionOut = Right)
    val scorekeeperAnimation = standardAnimation(SCOREKEEPER_ROUTE, directionIn = Left, directionOut = Left)

    AnimatedNavHost(
        navController = navController,
        startDestination = SCOREKEEPER_ROUTE,
    ) {
        composable(SCOREKEEPER_ROUTE,
            enterTransition =  timerAnimation.enterAnimation,
            exitTransition = scorekeeperAnimation.exitAnimation,
            popEnterTransition = timerAnimation.enterAnimation,
            popExitTransition = scorekeeperAnimation.exitAnimation,
        ) {
            ScorekeeperRoute(
                onNavigateToTimer = { navController.navigate(TIMERSET_ROUTE) },
                scorekeeperUiState = scorekeeperUiState,
                buttonClick = { scoringKey ->
                    scorekeeperViewModel.onScoringEvent(
                        ScoringEvent.OnScoringKeyPressed(scoringKey)
                    )
                },
                mins = mins.value,
                secs = secs.value,
                startStopText = startStopText.value,
            )
        }
        composable(TIMERSET_ROUTE,
        enterTransition = scorekeeperAnimation.enterAnimation,
        exitTransition = timerAnimation.exitAnimation,
        popEnterTransition = scorekeeperAnimation.enterAnimation,
        popExitTransition = timerAnimation.exitAnimation
        ) {
            TimersetRoute(
                onKeyClick = { key ->
                    scorekeeperViewModel.onTimersetEvent(
                        TimersetEvent.OnKeyPressed(key)) },
                timersetTimeState = timeState,
                onSetTimer = { navController.navigate(SCOREKEEPER_ROUTE) },
            )
        }
    }
}
