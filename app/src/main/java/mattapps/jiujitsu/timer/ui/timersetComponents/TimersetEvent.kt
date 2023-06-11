package mattapps.jiujitsu.timer.ui.timersetComponents

sealed class TimersetEvent{
    data class OnKeyPressed(val key: TimersetKeypad): TimersetEvent()
}