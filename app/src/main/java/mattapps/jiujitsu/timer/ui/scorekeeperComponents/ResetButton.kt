package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResetButton(
    scoringKey: ScoringKeypad,
    modifier: Modifier = Modifier,
    text: String,
    onLongClick: (ScoringKeypad) -> Unit,
) {
    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
    var shortClickCount by remember { mutableStateOf(0) }

    Surface(
    color = MaterialTheme.colorScheme.primaryContainer,
    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    shape = MaterialTheme.shapes.small,
    modifier = modifier
        .clip(shape = MaterialTheme.shapes.small)
        .combinedClickable(enabled = true,
            onClick = {haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                shortClickCount += 1
                if (shortClickCount == 3) {
                    Toast.makeText(context, "Long press to reset", Toast.LENGTH_SHORT).show()
                    shortClickCount = 0 } },
            onLongClick = {
                onLongClick(scoringKey)
                haptics.performHapticFeedback(HapticFeedbackType.LongPress) },
        )
        .wrapContentHeight()
        .fillMaxWidth(),
    border = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.outline
    ),
) {
    AutoResizeText(
        text = text,
        fontSizeRange = FontSizeRange(22.sp, 50.sp),
        textAlign = TextAlign.Center,
        maxLines = 1
    )
}
}