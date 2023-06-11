package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import mattapps.jiujitsu.timer.ui.model.ScorekeeperUiState
import mattapps.jiujitsu.timer.ui.model.ScorekeeperViewModel
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeUnit

@Composable
fun TimerDisplay(
    onNavigateToTimer: () -> Unit,
    timerStartClick: (ScoringKeypad) -> Unit,
    startStopText: String,
    mins: Int,
    secs: Int
    ) {
    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
    var hasNotified by remember { mutableStateOf(false)}
    var clickCounter by remember { mutableStateOf(0) }

    val timerModifier = when (startStopText) {
        "Pause", "Stop" -> Modifier.wrapContentWidth().padding(2.dp)
        else -> {
            Modifier.wrapContentWidth().padding(2.dp).clickable(onClick = onNavigateToTimer)
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            Modifier.weight(0.7F)
        )
        {
            AutoResizeText(
                text = "%02d:%02d".format(mins, secs),
                modifier = timerModifier,
                fontSizeRange = FontSizeRange(40.sp, 90.sp),
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Box (
            Modifier.weight(0.3F)
                ){
            Button(
                onClick = { timerStartClick(ScoringKeypad.StartTimer)
                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        clickCounter += 1
                        if (mins == 0 && secs == 0 && startStopText == "Start" && !hasNotified) {
                              Toast.makeText(context, "Tap timer to set time", Toast.LENGTH_SHORT).show()
                              hasNotified = true
                        }
                        if (clickCounter == 5) {
                              clickCounter = 0
                              hasNotified = false
                        }},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .width(200.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            ) {
                AutoResizeText(
                    text = startStopText,
                    modifier = Modifier.wrapContentSize(),
                    fontSizeRange = FontSizeRange(18.sp, 40.sp)
                )
            }
        }
    }
}