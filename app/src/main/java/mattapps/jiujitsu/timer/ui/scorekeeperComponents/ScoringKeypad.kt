package mattapps.jiujitsu.timer.ui.scorekeeperComponents

sealed class ScoringKeypad(
    val value: Int,
    val competitor: Int
) {
    object C1Add4: ScoringKeypad(4, 1)
    object C1Add3: ScoringKeypad(3,1)
    object C1Add2: ScoringKeypad(2,1)
    object C1Minus4: ScoringKeypad(-4,1)
    object C1Minus3: ScoringKeypad(-3,1)
    object C1Minus2: ScoringKeypad(-2,1)
    object C1AddAdv: ScoringKeypad(1, 1)
    object C1MinusAdv: ScoringKeypad(-1, 1)
    object C1AddPen: ScoringKeypad(1, 1)
    object C1MinusPen: ScoringKeypad(-1, 1)
    object C1Reset: ScoringKeypad(0,1)
    object C2Add4: ScoringKeypad(4, 2)
    object C2Add3: ScoringKeypad(3,2)
    object C2Add2: ScoringKeypad(2,2)
    object C2Minus4: ScoringKeypad(-4,2)
    object C2Minus3: ScoringKeypad(-3,2)
    object C2Minus2: ScoringKeypad(-2,2)
    object C2Reset: ScoringKeypad(0,2)
    object C2AddAdv: ScoringKeypad(1, 2)
    object C2MinusAdv: ScoringKeypad(-1, 2)
    object C2AddPen: ScoringKeypad(1, 2)
    object C2MinusPen: ScoringKeypad(-1, 2)
    object StartTimer: ScoringKeypad(0,0)
}