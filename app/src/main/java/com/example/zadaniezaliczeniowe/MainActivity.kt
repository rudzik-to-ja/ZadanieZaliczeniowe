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

@Composable
fun ForecastCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(219.dp)
            .clip(RoundedCornerShape(9.dp))
            .background(DarkPurpleCard)
            .padding(start = 38.dp, end = 38.dp, top = 31.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dzisiaj",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "1.12.2024",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(9.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Divider)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherHourItem(
                temp = "4.0°C",
                time = "8:00",
                type = WeatherIconType.Snow
            )

            WeatherHourItem(
                temp = "4.5°C",
                time = "10:00",
                type = WeatherIconType.Cloud
            )

            WeatherHourItem(
                temp = "4.7°C",
                time = "12:00",
                type = WeatherIconType.Cloud
            )

            WeatherHourItem(
                temp = "5.0°C",
                time = "14:00",
                type = WeatherIconType.PartlyCloudy
            )

            WeatherHourItem(
                temp = "5.0°C",
                time = "16:00",
                type = WeatherIconType.PartlyCloudy
            )

            WeatherHourItem(
                temp = "4.7°C",
                time = "18:00",
                type = WeatherIconType.PartlyCloudy
            )
        }
    }
}

enum class WeatherIconType {
    Snow,
    Cloud,
    PartlyCloudy
}

@Composable
fun WeatherHourItem(
    temp: String,
    time: String,
    type: WeatherIconType
) {
    Column(
        modifier = Modifier.size(width = 50.dp, height = 112.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = temp,
            color = White,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(15.dp))

        when (type) {
            WeatherIconType.Snow -> SnowCloudIcon(modifier = Modifier.size(48.dp))
            WeatherIconType.Cloud -> CloudIcon(modifier = Modifier.size(48.dp))
            WeatherIconType.PartlyCloudy -> PartlyCloudyIcon(modifier = Modifier.size(48.dp))
        }

        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = time,
            color = White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun SunIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.minDimension * 0.31f

        drawCircle(
            color = Yellow,
            radius = radius,
            center = center
        )

        val inner = size.minDimension * 0.44f
        val outer = size.minDimension * 0.58f

        for (i in 0 until 8) {
            val angle = Math.toRadians((i * 45).toDouble())
            val start = Offset(
                x = center.x + cos(angle).toFloat() * inner,
                y = center.y + sin(angle).toFloat() * inner
            )
            val end = Offset(
                x = center.x + cos(angle).toFloat() * outer,
                y = center.y + sin(angle).toFloat() * outer
            )

            drawLine(
                color = Yellow,
                start = start,
                end = end,
                strokeWidth = 4.2f,
                cap = StrokeCap.Square
            )
        }
    }
}