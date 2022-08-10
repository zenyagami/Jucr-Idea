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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.jucr.R
import com.zenkun.jucr.ui.theme.JucrAppTheme

@Composable
fun HomeHeaderView(
    userFirstName: String,
    chargingTimeLeftInMinutes: Int,
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
                text = stringResource(id = R.string.header_welcome, userFirstName),
                style = MaterialTheme.typography.labelLarge,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(progress),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Charging your car..",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(progress),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(100.dp))

        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.8f))) {
                append(stringResource(id = R.string.header_charging_eta_time_label))
            }

            withStyle(
                style = SpanStyle(
                    color = Color.White,
                )
            ) {
                append(
                    stringResource(
                        id = R.string.header_chargin_minutes_placeholder,
                        chargingTimeLeftInMinutes
                    )
                )
            }
        }

        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
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
        HomeHeaderView(chargingTimeLeftInMinutes = 30, userFirstName = "Javi")
    }
}