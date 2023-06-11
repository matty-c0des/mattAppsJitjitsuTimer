package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScorerButtonRow(
    buttonText: String,
    onClick: (ScoringKeypad) -> Unit,
    scoringKey1: ScoringKeypad,
    scoringKey2: ScoringKeypad
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(1.dp)
    ) {
        val scoreButtonModifier = Modifier
            .weight(0.5F)

        Spacer(
            Modifier.width(2.dp)
        )

        ScorerButton(
            modifier = scoreButtonModifier,
            text = "+$buttonText",
            onClick = onClick,
            scoringKey = scoringKey1
        )

        Spacer(
            Modifier.width(2.dp)
        )

        ScorerButton(
            modifier = scoreButtonModifier,
            text = "-$buttonText",
            onClick = onClick,
            scoringKey = scoringKey2
        )

        Spacer(
            Modifier.width(2.dp)
        )
    }
}