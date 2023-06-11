package mattapps.jiujitsu.timer.ui.model

import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeUnit

data class ScorekeeperUiState (
    val competitor1Score: Int = 0,
    val competitor1Adv: Int = 0,
    val competitor1Pen: Int = 0,
    val competitor2Score: Int = 0,
    val competitor2Adv: Int = 0,
    val competitor2Pen: Int = 0,
    var startStop: Boolean = false,
    var startStopText: String = "Start",
    var mins: Int = 0,
    var secs: Int = 0,
    var currentTime: Long = 0L,
    val interval: Long = 1000L
)