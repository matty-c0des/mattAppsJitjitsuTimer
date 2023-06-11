package mattapps.jiujitsu.timer.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import mattapps.jiujitsu.timer.ui.model.ScorekeeperUiState
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.BackPress
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.Scorekeeper
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.ScoringKeypad
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.TimerDisplay
import mattapps.jiujitsu.timer.ui.theme.mattAppsTheme

@Composable
fun ScorekeeperScreen(
    onNavigateToTimer: () -> Unit,
    buttonClick: (ScoringKeypad) -> Unit,
    scorekeeperUiState: ScorekeeperUiState,
    startStopText: String,
    mins: Int,
    secs: Int,
) {
    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if(showToast) {
        Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        showToast = false
    }

    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }


    mattAppsTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {Box(Modifier.wrapContentSize().weight(0.23F),
            contentAlignment = Alignment.BottomCenter) {
            TimerDisplay(
                onNavigateToTimer = onNavigateToTimer,
                timerStartClick = buttonClick,
                startStopText = startStopText,
                mins = mins,
                secs = secs
            )
        }
            Box(Modifier.wrapContentSize().weight(0.77F),
            contentAlignment = Alignment.BottomCenter) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(Modifier.wrapContentSize().weight(0.5F)) {
                        Scorekeeper(
                            competitor = 1,
                            competitorFirstName = "Competitor",
                            competitorLastName = "1",
                            scorekeeperUiState = scorekeeperUiState,
                            scoreButtonClick = buttonClick,
                            resetText = "Reset"
                        )
                    }

                    Box(Modifier.wrapContentSize().weight(0.5F)) {
                        Scorekeeper(
                            competitor = 2,
                            competitorFirstName = "Competitor",
                            competitorLastName = "2",
                            scorekeeperUiState = scorekeeperUiState,
                            scoreButtonClick = buttonClick,
                            resetText = "Reset"
                        )
                    }
                        Spacer(Modifier
                                .width(2.dp)
                        )

                }
            }

        }
    }
}