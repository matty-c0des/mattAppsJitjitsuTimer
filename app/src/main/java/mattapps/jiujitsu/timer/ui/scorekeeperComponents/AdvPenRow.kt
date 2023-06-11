package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdvPenRow(
    buttonText: String,
    scoringKey1: ScoringKeypad,
    scoringKey2: ScoringKeypad,
    onClick: (ScoringKeypad) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(2.dp)
    ) {
        val advPenButtonModifier = Modifier
            .wrapContentHeight()
        Spacer(
            Modifier.width(2.dp)
        )

        AdvPenButton(
            modifier = advPenButtonModifier.weight(0.5F),
            text = "A$buttonText",
            onClick = onClick,
            scoringKey = scoringKey1
        )

        Spacer(
            Modifier.width(2.dp)
        )

        AdvPenButton(
            modifier = advPenButtonModifier.weight(0.5F),
            text = "P$buttonText",
            onClick = onClick,
            scoringKey = scoringKey2
        )
        Spacer(
            Modifier.width(2.dp)
        )
    }
}