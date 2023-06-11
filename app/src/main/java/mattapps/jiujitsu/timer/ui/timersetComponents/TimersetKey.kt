package mattapps.jiujitsu.timer.ui.timersetComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mattapps.jiujitsu.timer.ui.theme.mattAppsTheme

@Composable
fun TimersetKey(
    key: TimersetKeypad,
    modifier: Modifier,
    icon: ImageVector? = null,
    onClick: ((TimersetKeypad) -> Unit)? = null,
    onSetTimer: (() -> Unit)? = null,
    visibility: Boolean = true
) {

    val haptics = LocalHapticFeedback.current

    mattAppsTheme() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(100.dp)
                )
                .clip(RoundedCornerShape(100.dp))
                .background(if (visibility == true) {MaterialTheme.colorScheme.primaryContainer} else {MaterialTheme.colorScheme.background})
                .clickable {
                    if (onClick != null) {
                        onClick(key)
                    }
                    if (onSetTimer != null) {
                        onSetTimer()
                    }
                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                }
                .then(modifier)
            ) {
            if (icon != null) {
                Icon(
                    icon,
                    modifier = Modifier.size(22.dp),
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            } else {
                Text(
                    key.value,
                    style = TextStyle(
                        fontSize = 34.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        }
    }
}