package com.jdrt.melitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jdrt.melitest.presentation.nvgraph.NavGraph
import com.jdrt.melitest.ui.theme.MeliTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MeliTestTheme {
                NavGraph()
            }
        }
    }
}
