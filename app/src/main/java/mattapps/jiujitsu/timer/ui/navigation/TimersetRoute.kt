package mattapps.jiujitsu.timer.ui.navigation

import androidx.compose.runtime.Composable
import mattapps.jiujitsu.timer.ui.screens.TimersetScreen
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetKeypad

@Composable
fun TimersetRoute(
    onKeyClick: ((TimersetKeypad) -> Unit)? = null,
    onSetTimer: (() -> Unit)? = null,
    timersetTimeState: TimeState,
) {
    TimersetScreen(
        onKeyClick = { key ->
            if (onKeyClick != null) {
                onKeyClick(key)
            }
        },
        onSetTimer = onSetTimer,
        timersetTimeState = timersetTimeState
    )
}