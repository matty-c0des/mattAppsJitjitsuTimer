package mattapps.jiujitsu.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import mattapps.jiujitsu.timer.ui.navigation.TimerNavHost
import mattapps.jiujitsu.timer.ui.theme.mattAppsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mattAppsTheme {
                TimerNavHost()
            }
        }
    }
}