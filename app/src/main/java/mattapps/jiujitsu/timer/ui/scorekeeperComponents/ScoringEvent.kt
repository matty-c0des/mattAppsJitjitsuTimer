package mattapps.jiujitsu.timer.ui.scorekeeperComponents

sealed class ScoringEvent{
    data class OnScoringKeyPressed(val key: ScoringKeypad): ScoringEvent()
}