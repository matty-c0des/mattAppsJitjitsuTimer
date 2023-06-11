package mattapps.jiujitsu.timer.ui.navigation

import androidx.compose.runtime.Composable
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.ScoringKeypad
import mattapps.jiujitsu.timer.ui.screens.ScorekeeperScreen
import mattapps.jiujitsu.timer.ui.model.ScorekeeperUiState
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState

@Composable
fun ScorekeeperRoute(
    onNavigateToTimer: () -> Unit,
    scorekeeperUiState: ScorekeeperUiState,
    buttonClick: (ScoringKeypad) -> Unit,
    startStopText: String,
    mins: Int,
    secs: Int,
) {
    ScorekeeperScreen(
        onNavigateToTimer = onNavigateToTimer,
        scorekeeperUiState = scorekeeperUiState,
        buttonClick = buttonClick,
        startStopText = startStopText,
        mins = mins,
        secs = secs,
    )
}