package mattapps.jiujitsu.timer.ui.scorekeeperComponents

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

