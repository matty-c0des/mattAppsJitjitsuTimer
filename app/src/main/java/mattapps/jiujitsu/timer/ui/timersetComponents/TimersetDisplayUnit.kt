package mattapps.jiujitsu.timer.ui.timersetComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TimersetDisplayUnit(
    time: TimeUnit = TimeUnit(),
    unit: String,
) {
    val textColor = MaterialTheme.colorScheme.onBackground

    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "${time.leftDigit}",
            modifier = Modifier.alignByBaseline(),
            style = TextStyle(
                fontSize = 65.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        )
        Text(
            text = "${time.rightDigit}",
            modifier = Modifier.alignByBaseline(),
            style = TextStyle(
                fontSize = 65.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        )
        Text(
            text = unit,
            modifier = Modifier.alignByBaseline(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        )
    }
}