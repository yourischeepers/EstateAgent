package me.partypronl.estateagent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import me.partypronl.estateagent.navigation.RootNavHost
import me.partypronl.estateagent.generic.theme.EstateAgentTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EstateAgentTheme {
                RootNavHost(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
