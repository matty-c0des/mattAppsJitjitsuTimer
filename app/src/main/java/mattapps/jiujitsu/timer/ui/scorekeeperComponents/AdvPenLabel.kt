package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mattapps.jiujitsu.timer.ui.model.ScorekeeperUiState
import mattapps.jiujitsu.timer.ui.theme.mattAppsTheme

@Composable
fun AdvPenLabel(
    competitor: Int,
    scorekeeperUiState: ScorekeeperUiState
) { mattAppsTheme {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            val textModifier = Modifier
                .wrapContentSize()
                .padding(5.dp)
            val textSize = FontSizeRange(20.sp, 50.sp)
            val textColor = MaterialTheme.colorScheme.onBackground

            AutoResizeText("A", modifier = textModifier, fontSizeRange = textSize, color = textColor)

            if (competitor == 1) {
                AutoResizeText(
                    "${scorekeeperUiState.competitor1Adv}",
                    modifier = textModifier,
                    fontSizeRange = textSize,
                    color = textColor
                )
            } else if (competitor == 2) {
                AutoResizeText(
                    "${scorekeeperUiState.competitor2Adv}",
                    modifier = textModifier,
                    fontSizeRange = textSize,
                    color = textColor
                )
            }

            Spacer(
                Modifier
                    .width(30.dp)
            )

            if (competitor == 1) {
                AutoResizeText(
                    "${scorekeeperUiState.competitor1Pen}",
                    modifier = textModifier,
                    fontSizeRange = textSize,
                    color = textColor
                )
            } else if (competitor == 2) {
                AutoResizeText(
                    "${scorekeeperUiState.competitor2Pen}",
                    modifier = textModifier,
                    fontSizeRange = textSize,
                    color = textColor
                )
            }

            AutoResizeText("P", modifier = textModifier, fontSizeRange = textSize, color = textColor)
        }
    }
}