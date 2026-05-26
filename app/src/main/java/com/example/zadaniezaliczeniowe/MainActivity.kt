package com.example.weatherstatic

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val window: Window = window
        window.statusBarColor = android.graphics.Color.rgb(117, 117, 117)
        window.navigationBarColor = android.graphics.Color.BLACK

        setContent {
            WeatherStaticScreen()
        }
    }
}

private val PurpleBackground = Color(0xFF5B27B5)
private val DarkPurpleCard = Color(0xFF4A2496)
private val Yellow = Color(0xFFFFC815)
private val OrangeButton = Color(0xFFFFB23B)
private val CloudBlue = Color(0xFF61BFEF)
private val White = Color.White
private val Divider = Color(0xFF9B7AD6)

@Composable
fun WeatherStaticScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PurpleBackground
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PurpleBackground)
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SunIcon(
                    modifier = Modifier.size(94.dp)
                )

                Spacer(modifier = Modifier.height(17.dp))

                Text(
                    text = "5.0°C",
                    color = White,
                    fontSize = 37.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 42.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Warszawa",
                    color = White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(31.dp))

                ForecastCard()

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .padding(horizontal = 21.dp)
                        .fillMaxWidth()
                        .height(49.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(OrangeButton),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sprawdź pozostałe dni",
                        color = White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}