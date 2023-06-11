package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mattapps.jiujitsu.timer.ui.theme.mattAppsTheme

@Composable
fun AdvPenButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (ScoringKeypad) -> Unit,
    scoringKey: ScoringKeypad
) {
    val haptics = LocalHapticFeedback.current
    mattAppsTheme() {
        Button(
            onClick = { onClick(scoringKey)
                haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
            shape = MaterialTheme.shapes.small,
            modifier = modifier,
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
        ) {
            AutoResizeText(
                text = text,
                fontSizeRange = FontSizeRange(18.sp, 50.sp),
                maxLines = 1
            )
        }
    }
}