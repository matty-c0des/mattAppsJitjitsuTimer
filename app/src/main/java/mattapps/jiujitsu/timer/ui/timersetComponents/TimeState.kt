package mattapps.jiujitsu.timer.ui.timersetComponents

data class TimeState(
    var mins: TimeUnit = TimeUnit(),
    var secs: TimeUnit = TimeUnit(),
) {
    fun isDataFull() = mins.leftDigit > 0

    fun isDataEmpty() =
        mins.leftDigit == 0 && mins.rightDigit == 0
                && secs.leftDigit == 0 && secs.rightDigit == 0

    fun isMinsHalfFull() = mins.leftDigit == 0 && mins.rightDigit > 0
}