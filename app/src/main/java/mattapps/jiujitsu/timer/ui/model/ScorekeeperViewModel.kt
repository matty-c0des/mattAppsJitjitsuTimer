package mattapps.jiujitsu.timer.ui.model

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.ScoringEvent
import mattapps.jiujitsu.timer.ui.scorekeeperComponents.ScoringKeypad
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeUnit
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetEvent
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetKeypad
import java.lang.Integer.max

class ScorekeeperViewModel : ViewModel() {
    private val _scorekeeperUiState = MutableStateFlow(ScorekeeperUiState())
    val scorekeeperUiState: StateFlow<ScorekeeperUiState> = _scorekeeperUiState.asStateFlow()

    fun onScoringEvent(event: ScoringEvent) {
        when (event) {
            is ScoringEvent.OnScoringKeyPressed -> {
                onScorekeeperKeyPress(event.key)
            }
        }
    }

    private fun onScorekeeperKeyPress(key: ScoringKeypad) {
        return when (key) {
            ScoringKeypad.C1Reset -> {
                resetScorekeeper(key.competitor)
            }

            ScoringKeypad.C2Reset -> {
                resetScorekeeper(key.competitor)
            }

            ScoringKeypad.C1AddAdv -> {
                advPenChange("Adv", key.competitor, key.value)
            }

            ScoringKeypad.C1AddPen -> {
                advPenChange("Pen", key.competitor, key.value)
            }

            ScoringKeypad.C1MinusAdv -> {
                advPenChange("Adv", key.competitor, key.value)
            }

            ScoringKeypad.C1MinusPen -> {
                advPenChange("Pen", key.competitor, key.value)
            }

            ScoringKeypad.C2AddAdv -> {
                advPenChange("Adv", key.competitor, key.value)
            }

            ScoringKeypad.C2AddPen -> {
                advPenChange("Pen", key.competitor, key.value)
            }

            ScoringKeypad.C2MinusAdv -> {
                advPenChange("Adv", key.competitor, key.value)
            }

            ScoringKeypad.C2MinusPen -> {
                advPenChange("Pen", key.competitor, key.value)
            }
            ScoringKeypad.StartTimer -> {
                onStartClicked()
                deleteAllTime()
            }

            else -> {
                scoreChange(key.competitor, key.value)
            }
        }
    }

    private fun resetScorekeeper(competitor: Int) {
        if (competitor == 1) {
            _scorekeeperUiState.update { currentState ->
                currentState.copy(
                    competitor1Score = 0,
                    competitor1Adv = 0,
                    competitor1Pen = 0
                )
            }
        } else if (competitor == 2) {
            _scorekeeperUiState.update { currentState ->
                currentState.copy(
                    competitor2Score = 0,
                    competitor2Adv = 0,
                    competitor2Pen = 0
                )
            }
        }
    }



    private fun scoreChange(
        competitor: Int,
        value: Int
    ) {
        if (competitor == 1) {
            val newScore = _scorekeeperUiState.value.competitor1Score.plus(value)
            updateScoreState(competitor, max(newScore, 0))
        } else {
            val newScore = _scorekeeperUiState.value.competitor2Score.plus(value)
            updateScoreState(competitor, max(newScore, 0))
        }

    }

    private fun advPenChange(
        advOrPen: String,
        competitor: Int,
        value: Int
    ) {
        if (competitor == 1) {
            if (advOrPen == "Adv") {
                val newAdv = _scorekeeperUiState.value.competitor1Adv.plus(value)
                updateAdvPenState(competitor, max(newAdv, 0), advOrPen)
            } else {
                val newPen = _scorekeeperUiState.value.competitor1Pen.plus(value)
                updateAdvPenState(competitor, max(newPen, 0), advOrPen)
            }
        } else if (competitor == 2) {
            if (advOrPen == "Adv") {
                val newAdv = _scorekeeperUiState.value.competitor2Adv.plus(value)
                updateAdvPenState(competitor, max(newAdv, 0), advOrPen)
            } else {
                val newPen = _scorekeeperUiState.value.competitor2Pen.plus(value)
                updateAdvPenState(competitor, max(newPen, 0), advOrPen)
            }
        }
    }

    private fun updateAdvPenState(competitor: Int, updatedScore: Int, advOrPen: String) {
        if (competitor == 1) {
            if (advOrPen == "Adv") {
                _scorekeeperUiState.update { currentState ->
                    currentState.copy(
                        competitor1Adv = updatedScore
                    )
                }
            } else if (advOrPen == "Pen") {
                _scorekeeperUiState.update { currentState ->
                    currentState.copy(
                        competitor1Pen = updatedScore
                    )
                }
            }
        } else if (competitor == 2) {
            if (advOrPen == "Adv") {
                _scorekeeperUiState.update { currentState ->
                    currentState.copy(
                        competitor2Adv = updatedScore
                    )
                }
            } else if (advOrPen == "Pen") {
                _scorekeeperUiState.update { currentState ->
                    currentState.copy(
                        competitor2Pen = updatedScore
                    )
                }
            }
        }
    }

    private fun updateScoreState(competitor: Int, updatedScore: Int) {
        if (competitor == 1) {
            _scorekeeperUiState.update { currentState ->
                currentState.copy(
                    competitor1Score = updatedScore
                )
            }
        } else if (competitor == 2) {
            _scorekeeperUiState.update { currentState ->
                currentState.copy(
                    competitor2Score = updatedScore
                )
            }
        }
    }

    // Time setter states below
    private val _timeState = MutableStateFlow(TimeState())
    val timeState: StateFlow<TimeState> = _timeState.asStateFlow()

    fun onTimersetEvent(event: TimersetEvent) {
        when (event) {
            is TimersetEvent.OnKeyPressed -> {
                onTimerKeyPress(event.key)
            }
        }
    }

    private fun onTimerKeyPress(key: TimersetKeypad) {
        when (key) {
            TimersetKeypad.KeySet -> {
                passTime()
                _startStopText.value = "Start"
            }

            TimersetKeypad.KeyDelete -> {
                if (_timeState.value.isDataEmpty())
                    return
                deleteTime()
            }

            TimersetKeypad.Key00 -> {
                if (_timeState.value.isDataEmpty()
                    || _timeState.value.isMinsHalfFull()
                    || _timeState.value.isDataFull()
                )
                    return
                addTime(TimersetKeypad.Key0.value)
                addTime(TimersetKeypad.Key0.value)
            }

            TimersetKeypad.Key0 -> {
                /* when zero key is clicked */
                if (_timeState.value.isDataEmpty()
                    || _timeState.value.isDataFull()
                )
                    return
                addTime(key.value)
            }

            else -> {
                /* when any num key is clicked */
                if (_timeState.value.isDataFull())
                    return
                addTime(key.value)
            }
        }
    }

    private fun deleteAllTime() {
        val secs = TimeUnit(
            rightDigit = 0,
            leftDigit = 0
        )
        val mins = TimeUnit (
            rightDigit = 0,
            leftDigit = 0
        )
        _timeState.update { currentState ->
            currentState.copy(
                mins = mins,
                secs = secs
            )
        }
    }

    private fun deleteTime() {
        val secs = TimeUnit(
            rightDigit = _timeState.value.secs.leftDigit,
            leftDigit = _timeState.value.mins.rightDigit,
        )
        val mins = TimeUnit(
            rightDigit = _timeState.value.mins.leftDigit,
            leftDigit = 0,
        )
        _timeState.update { currentState ->
            currentState.copy(
                mins = mins,
                secs = secs
            )
        }
    }

    private fun addTime(value: String) {
        val intValue = value.toInt()

        val mins = TimeUnit(
            leftDigit = _timeState.value.mins.rightDigit,
            rightDigit = _timeState.value.secs.leftDigit,
        )
        val secs = TimeUnit(
            leftDigit = _timeState.value.secs.rightDigit,
            rightDigit = intValue,
        )
        _timeState.update { currentState ->
            currentState.copy(
                mins = mins,
                secs = secs
            )
        }
    }

    private fun passTime() {
        val timeState = _timeState.value
        _mins.value = timeState.mins.leftDigit * 10 + timeState.mins.rightDigit
        _secs.value = timeState.secs.leftDigit * 10 + timeState.secs.rightDigit
        _currentTime.value = (_mins.value * 60 + _secs.value) * 1000L
        }

    private var timer: CountDownTimer? = null

    private val _currentTime = MutableStateFlow(0L)

    private val _mins = MutableStateFlow(0)
    val mins: StateFlow<Int> = _mins

    private val _secs = MutableStateFlow(0)
    val secs: StateFlow<Int> = _secs

    private val _startStopText = MutableStateFlow("Start")
    val startStopText: StateFlow<String> = _startStopText

    private fun startTimer() {
        timer = object : CountDownTimer(_currentTime.value, scorekeeperUiState.value.interval) {
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished
                val totalSeconds = millisUntilFinished / 1000
                _mins.value = totalSeconds.toInt() / 60
                _secs.value = totalSeconds.toInt() % 60
            }

            override fun onFinish() {
                _currentTime.value = 0L
                _startStopText.value = "Stop"
                mediaPlayerController?.start()
            }
        }.start()
    }

    var mediaPlayerController: MediaPlayerController? = null

    private fun onStartClicked() {

        when (startStopText.value) {
            "Start" -> {
                if (_currentTime.value > 0L) {
                    _startStopText.value = "Pause"
                    _currentTime.value += 1000L
                    startTimer()
                }
            }
            "Pause" -> {
                timer?.cancel()
                _startStopText.value = "Resume"
                mediaPlayerController?.stop()
                mediaPlayerController?.reset()
            }
            "Resume" -> {
                _startStopText.value = "Pause"
                startTimer()
            }
            else -> {
                _startStopText.value = "Start"
                mediaPlayerController?.stop()
                mediaPlayerController?.reset()
            }
        }
    }
    interface MediaPlayerController {
        fun start()
        fun stop()
        fun reset()
    }
}