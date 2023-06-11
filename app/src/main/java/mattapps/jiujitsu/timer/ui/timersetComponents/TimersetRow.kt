package mattapps.jiujitsu.timer.ui.timersetComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TimersetRow(
    key1: TimersetKeypad,
    key2: TimersetKeypad,
    key3: TimersetKeypad,
    icon: ImageVector? = null,
    modifier: Modifier,
    onClick: (TimersetKeypad) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(1.dp)
    ) {
        TimersetKey(key = key1, modifier = modifier, onClick = onClick, icon = null)
        Spacer(Modifier.padding(1.dp))
        TimersetKey(key = key2, modifier = modifier, onClick = onClick, icon = null)
        Spacer(Modifier.padding(1.dp))
        TimersetKey(key = key3, modifier = modifier, onClick = onClick, icon = icon)
    }
}