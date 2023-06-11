package mattapps.jiujitsu.timer.ui.scorekeeperComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mattapps.jiujitsu.timer.ui.model.ScorekeeperUiState

@Composable
fun Scorekeeper(
    competitor: Int,
    competitorFirstName: String,
    competitorLastName: String,
    scoreButtonClick: (ScoringKeypad) -> Unit,
    scorekeeperUiState: ScorekeeperUiState,
    resetText: String
) {
    val textColor = MaterialTheme.colorScheme.onBackground


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            Modifier
                .wrapContentSize()
                .weight(0.8F)) {
            AutoResizeText(
                if (competitor == 1) {
                    scorekeeperUiState.competitor1Score.toString()
                } else {
                    scorekeeperUiState.competitor2Score.toString()
                       },
                modifier = Modifier
                    .wrapContentHeight(),
                fontSizeRange = FontSizeRange(40.sp, 100.sp),
                color = textColor,
                maxLines = 1
            )
        }

        Box(
            Modifier
                .wrapContentSize()
                .weight(0.45F))
        { AdvPenLabel(competitor = competitor, scorekeeperUiState = scorekeeperUiState) }

        val advPenRow1Button1 = if (competitor == 1) {
            ScoringKeypad.C1AddAdv
        } else {
            ScoringKeypad.C2AddAdv
        }
        val advPenRow1Button2 = if (competitor == 1) {
            ScoringKeypad.C1AddPen
        } else {
            ScoringKeypad.C2AddPen
        }
        val advPenRow2Button1 = if (competitor == 1) {
            ScoringKeypad.C1MinusAdv
        } else {
            ScoringKeypad.C2MinusAdv
        }
        val advPenRow2Button2 = if (competitor == 1) {
            ScoringKeypad.C1MinusPen
        } else {
            ScoringKeypad.C2MinusPen
        }

        Box(
            Modifier
                .wrapContentSize()
                .weight(0.45F)) {
            AdvPenRow(
                buttonText = "+",
                scoringKey1 = advPenRow1Button1,
                scoringKey2 = advPenRow1Button2,
                onClick = scoreButtonClick
            )
        }

        Box(
            Modifier
                .wrapContentSize()
                .weight(0.45F)) {
            AdvPenRow(
                buttonText = "-",
                scoringKey1 = advPenRow2Button1,
                scoringKey2 = advPenRow2Button2,
                onClick = scoreButtonClick
            )
        }

        Box(
            Modifier
                .wrapContentSize()
                .weight(0.45F)) {
            AutoResizeText(
                "$competitorFirstName $competitorLastName",
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 5.dp)
                    .wrapContentHeight(),
                fontSizeRange = FontSizeRange(18.sp, 50.sp),
                color = textColor,
                maxLines = 1
            )
        }
        val scoreRow1Button1 = if (competitor == 1) {
           ScoringKeypad.C1Add4
        } else {
            ScoringKeypad.C2Add4
        }
        val scoreRow1Button2 = if (competitor == 1) {
            ScoringKeypad.C1Minus4
        } else {
            ScoringKeypad.C2Minus4
        }

        val scoreRow2Button1 = if (competitor == 1) {
            ScoringKeypad.C1Add3
        } else {
            ScoringKeypad.C2Add3
        }
        val scoreRow2Button2 = if (competitor == 1) {
            ScoringKeypad.C1Minus3
        } else {
            ScoringKeypad.C2Minus3
        }
        val scoreRow3Button1 = if (competitor == 1) {
            ScoringKeypad.C1Add2
        } else {
            ScoringKeypad.C2Add2
        }
        val scoreRow3Button2 = if (competitor == 1) {
            ScoringKeypad.C1Minus2
        } else {
            ScoringKeypad.C2Minus2
        }

        val resetButton = if (competitor == 1) {
            ScoringKeypad.C1Reset
        } else {
            ScoringKeypad.C2Reset
        }

        Box(
            Modifier
                .wrapContentSize()
                .weight(0.45F)
            ) {
            ScorerButtonRow(
                buttonText = "4",
                onClick = scoreButtonClick,
                scoringKey1 = scoreRow1Button1,
                scoringKey2 = scoreRow1Button2
            )
            }
            Spacer(Modifier.size(2.dp))
            Box(
            Modifier
                .wrapContentSize()
                .weight(0.45F)
            ) {
            ScorerButtonRow(
                buttonText = "3",
                onClick = scoreButtonClick,
                scoringKey1 = scoreRow2Button1,
                scoringKey2 = scoreRow2Button2
            )
            }
            Spacer(Modifier.size(2.dp))
            Box (
            Modifier
                .wrapContentSize()
                .weight(0.45F)
            ) {
            ScorerButtonRow(
                buttonText = "2",
                onClick = scoreButtonClick,
                scoringKey1 = scoreRow3Button1,
                scoringKey2 = scoreRow3Button2
            )
            }

            Box(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .weight(0.45F)
            ) {
            ResetButton(
                text = resetText,
                onLongClick = scoreButtonClick,
                scoringKey = resetButton,
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 7.dp)
            )
            }
    }
}