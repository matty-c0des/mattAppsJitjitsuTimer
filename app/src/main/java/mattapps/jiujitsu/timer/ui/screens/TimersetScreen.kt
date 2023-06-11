package mattapps.jiujitsu.timer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Backspace
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mattapps.jiujitsu.timer.ui.timersetComponents.TimeState
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetKeypad
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetDisplay
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetKey
import mattapps.jiujitsu.timer.ui.timersetComponents.TimersetRow

@Composable
fun TimersetScreen(
    onKeyClick: (TimersetKeypad) -> Unit,
    onSetTimer: (() -> Unit)? = null,
    timersetTimeState: TimeState
) {

    val isSetButtonActive = timersetTimeState.isDataEmpty().not()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val timersetKeyModifier = Modifier
            .height(96.dp)
            .width(96.dp)
            .padding(4.dp)

        TimersetDisplay(time = timersetTimeState)
        TimersetRow(key1 = TimersetKeypad.Key7, key2 = TimersetKeypad.Key8, key3 = TimersetKeypad.Key9, icon = null, modifier = timersetKeyModifier, onClick = onKeyClick)
        TimersetRow(key1 = TimersetKeypad.Key4, key2 = TimersetKeypad.Key5, key3 = TimersetKeypad.Key6, icon = null, modifier = timersetKeyModifier, onClick = onKeyClick)
        TimersetRow(key1 = TimersetKeypad.Key1, key2 = TimersetKeypad.Key2, key3 = TimersetKeypad.Key3, icon = null, modifier = timersetKeyModifier, onClick = onKeyClick)
        TimersetRow(key1 = TimersetKeypad.Key00, key2 = TimersetKeypad.Key0, key3 = TimersetKeypad.KeyDelete, icon = Icons.Outlined.Backspace, modifier = timersetKeyModifier, onClick = onKeyClick)
        TimersetKey(key = TimersetKeypad.KeySet, modifier = timersetKeyModifier, onSetTimer = onSetTimer, onClick = onKeyClick, visibility = isSetButtonActive)
    }
}