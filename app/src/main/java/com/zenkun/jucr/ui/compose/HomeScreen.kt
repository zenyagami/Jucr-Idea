package com.zenkun.jucr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.jucr.R
import com.zenkun.jucr.ui.theme.JucrAppTheme
import com.zenkun.jucr.ui.theme.PrimarySurface

@Composable
fun HomeScreen() {

}

@Composable
private fun HomeScreenContent(
    stationList: List<StationModel>
) {
    val lazyColumnState = rememberLazyListState()
    val lazyRowState = rememberLazyListState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.car_statistics_label),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.4f
                    )
                )
            }
        }

        LazyRow(
            state = lazyRowState,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                StatisticsRowView(
                    title = "240 Volt",
                    description = "Voltage"
                )
            }
            item {
                StatisticsRowView(
                    title = "240 Volt",
                    description = "Voltage"
                )
            }
            item {
                StatisticsRowView(
                    title = "240 Volt",
                    description = "Voltage"
                )
            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.nearby_super_chargers_label),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )

            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.super_chargers_view_all_label),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            state = lazyColumnState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(stationList) {
                StationRowView(stationModel = it)
            }
        }
    }
}

data class StationModel(
    val address: String,
    val currentAvailableCharger: Int,
    val maxAvailableChargers: Int,
    val isFavorite: Boolean,
    val distance: String,
)

@Composable
private fun StationRowView(
    stationModel: StationModel,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)),
        color = PrimarySurface,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stationModel.address,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${stationModel.currentAvailableCharger} / ${stationModel.maxAvailableChargers}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = if (stationModel.isFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.LocationOn
                    }, contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stationModel.distance,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewStationRowView() {
    JucrAppTheme {
        StationRowView(
            getMockedStations().first()
        )
    }
}

private fun getMockedStations(): List<StationModel> {
    return listOf(
        StationModel(
            address = "Ranchview Dr. Richardson",
            currentAvailableCharger = 4,
            maxAvailableChargers = 10,
            isFavorite = false,
            distance = "1.3km"
        ),
        StationModel(
            address = "Berlinerstr 21",
            currentAvailableCharger = 2,
            maxAvailableChargers = 10,
            isFavorite = true,
            distance = "1.3km"
        ),
        StationModel(
            address = "Torstrasse 12",
            currentAvailableCharger = 3,
            maxAvailableChargers = 4,
            isFavorite = false,
            distance = "1.3km"
        ),
        StationModel(
            address = "Street 23",
            currentAvailableCharger = 3,
            maxAvailableChargers = 10,
            isFavorite = true,
            distance = "1.3km"
        ),
        StationModel(
            address = "ALlegro 232",
            currentAvailableCharger = 3,
            maxAvailableChargers = 10,
            isFavorite = false,
            distance = "1.3km"
        ),
        StationModel(
            address = "Mvv station 232",
            currentAvailableCharger = 6,
            maxAvailableChargers = 10,
            isFavorite = false,
            distance = "1.4km"
        ),
    )
}

@Composable
fun StatisticsRowView(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(130.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        color = Color.Red.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(1)
                    )
                    .scale(0.65f),
                tint = Color.Red,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
@Preview
private fun PreviewStatisticsRowView() {
    JucrAppTheme {
        StatisticsRowView(
            title = "240 Volt",
            description = "Voltage"
        )
    }
}


@Composable
@Preview(showSystemUi = true)
fun PreviewHomeScreenContent() {
    JucrAppTheme {
        Column(modifier = Modifier.padding(24.dp)) {
            HomeScreenContent(stationList = getMockedStations())
        }
    }
}