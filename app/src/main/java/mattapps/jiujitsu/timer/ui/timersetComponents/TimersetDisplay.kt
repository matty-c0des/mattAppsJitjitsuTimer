package mattapps.jiujitsu.timer.ui.timersetComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun TimersetDisplay(
    time: TimeState = TimeState(),
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TimersetDisplayUnit(
            time = time.mins,
            unit = "m"
        )

        TimersetDisplayUnit(
            time = time.secs,
            unit = "s"
        )
    }
}