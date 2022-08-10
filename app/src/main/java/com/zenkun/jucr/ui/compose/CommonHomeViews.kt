package com.zenkun.jucr.ui.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zenkun.jucr.ui.theme.JucrAppTheme


@Composable
fun CircularProgressCharging(
    percentage: Float,
    number: Int,
    radius: Dp = 50.dp,
    color: Color = JucrAppTheme.colors.success,
    strokeWidth: Dp = 6.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    modifier: Modifier = Modifier
) {
    var animationPlayed by remember { mutableStateOf(false) }
    val currentPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) {
            percentage
        } else {
            0f
        },
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )
    LaunchedEffect(key1 = true, block = {
        animationPlayed = true
    })
    Box(
        modifier = modifier
            .size(radius * 2)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(radius * 2), onDraw = {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        })
        Column {
            Icon(
                imageVector = Icons.Default.ElectricBolt,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = (currentPercentage * number).toInt().toString(),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCircularProgressCharging() {
    JucrAppTheme {
        Box(modifier = Modifier.background(color = JucrAppTheme.colors.red)) {
            CircularProgressCharging(
                percentage = 0.8f,
                number = 100,
                radius = 44.dp
            )
        }
    }
}