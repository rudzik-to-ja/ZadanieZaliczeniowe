package com.example.zadaniezaliczeniowe

import android.os.Bundle
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

@Composable
fun CloudIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        drawCloud()
    }
}

@Composable
fun PartlyCloudyIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val sunCenter = Offset(size.width * 0.68f, size.height * 0.37f)
        val sunRadius = size.width * 0.18f

        drawCircle(
            color = Yellow,
            radius = sunRadius,
            center = sunCenter
        )

        for (i in 0 until 8) {
            val angle = Math.toRadians((i * 45).toDouble())
            val inner = sunRadius * 1.45f
            val outer = sunRadius * 2.05f

            drawLine(
                color = Yellow,
                start = Offset(
                    sunCenter.x + cos(angle).toFloat() * inner,
                    sunCenter.y + sin(angle).toFloat() * inner
                ),
                end = Offset(
                    sunCenter.x + cos(angle).toFloat() * outer,
                    sunCenter.y + sin(angle).toFloat() * outer
                ),
                strokeWidth = 2.4f,
                cap = StrokeCap.Square
            )
        }

        drawCloud(offsetY = size.height * 0.14f)
    }
}

@Composable
fun SnowCloudIcon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        drawCloud(offsetY = 0f)

        val snowColor = Color(0xFFBFDFFF)
        val startY = size.height * 0.72f
        val xs = listOf(0.27f, 0.43f, 0.58f, 0.73f)

        xs.forEachIndexed { index, x ->
            val cx = size.width * x
            val cy = startY + if (index % 2 == 0) 0f else size.height * 0.1f

            drawLine(
                color = snowColor,
                start = Offset(cx - 4f, cy),
                end = Offset(cx + 4f, cy),
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = snowColor,
                start = Offset(cx, cy - 4f),
                end = Offset(cx, cy + 4f),
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = snowColor,
                start = Offset(cx - 3f, cy - 3f),
                end = Offset(cx + 3f, cy + 3f),
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
            drawLine(
                color = snowColor,
                start = Offset(cx - 3f, cy + 3f),
                end = Offset(cx + 3f, cy - 3f),
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawCloud(
    offsetY: Float = 0f
) {
    val y = offsetY

    drawCircle(
        color = CloudBlue,
        radius = size.width * 0.19f,
        center = Offset(size.width * 0.34f, y + size.height * 0.52f)
    )

    drawCircle(
        color = CloudBlue,
        radius = size.width * 0.25f,
        center = Offset(size.width * 0.52f, y + size.height * 0.43f)
    )

    drawCircle(
        color = CloudBlue,
        radius = size.width * 0.18f,
        center = Offset(size.width * 0.70f, y + size.height * 0.53f)
    )

    drawRoundRect(
        color = CloudBlue,
        topLeft = Offset(size.width * 0.18f, y + size.height * 0.48f),
        size = androidx.compose.ui.geometry.Size(
            width = size.width * 0.68f,
            height = size.height * 0.26f
        ),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(
            x = size.width * 0.13f,
            y = size.width * 0.13f
        )
    )
}