package mattapps.jiujitsu.timer.ui.timersetComponents

sealed class TimersetKeypad(
    val value: String,
) {
    object Key1: TimersetKeypad("1")
    object Key2: TimersetKeypad("2")
    object Key3: TimersetKeypad("3")
    object Key4: TimersetKeypad("4")
    object Key5: TimersetKeypad("5")
    object Key6: TimersetKeypad("6")
    object Key7: TimersetKeypad("7")
    object Key8: TimersetKeypad("8")
    object Key9: TimersetKeypad("9")
    object Key0: TimersetKeypad("0")
    object Key00: TimersetKeypad("00")
    object KeyDelete: TimersetKeypad("x")
    object KeySet: TimersetKeypad("SET")
}