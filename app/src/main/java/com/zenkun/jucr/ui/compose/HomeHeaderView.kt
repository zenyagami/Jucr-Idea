package com.zenkun.jucr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.jucr.ui.theme.JucrAppTheme

@Composable
fun HomeHeaderView(
    progress: Float = 1f,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(JucrAppTheme.colors.red),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.height(80.dp)) {
            Text(
                text = "Good morning Javi",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(progress),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Charging your car..",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(progress),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = "Time to end the charge: 49 Min",
            color = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .fillMaxWidth()
                .alpha(progress),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
fun PreviewHomeHeaderView() {
    JucrAppTheme {
        HomeHeaderView()
    }
}