package com.zenkun.jucr.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BabyChangingStation
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenkun.domain.car.model.ChargingStatistics
import com.zenkun.jucr.R
import com.zenkun.jucr.ui.theme.JucrAppTheme
import com.zenkun.jucr.ui.theme.PrimarySurface
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun HomeScreen() {

}

@Composable
private fun HomeScreenContent(
    stationList: List<StationModel>,
    statisticsList: List<ChargingStatistics>,
    currentCharging: Int,
) {
    val lazyColumnState = rememberLazyListState()
    val lazyRowState = rememberLazyListState()
    val state = rememberCollapsingToolbarScaffoldState()

    val progress by remember {
        derivedStateOf {
            state.toolbarState.progress
        }
    }
    CollapsingToolbarScaffold(
        toolbar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .pin(),
            )

            Box {
                HomeHeaderView(
                    userFirstName = "Javi",
                    progress = progress,
                    //modifier = Modifier.parallax(0.3f),
                    chargingTimeLeftInMinutes = 44,
                    currentCharging = currentCharging
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(1f - progress),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Audi RS3",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(
                            id = R.string.header_compact_view_charging_percentage_label,
                            currentCharging
                        ),
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.audi_side_sample),
                contentDescription = null,
                modifier = Modifier
                    .size(height = 210.dp, width = 240.dp)
                    .road(
                        whenCollapsed = Alignment.CenterEnd,
                        whenExpanded = Alignment.Center
                    )
                    .scale(
                        if (progress <= 0.55) {
                            0.55f
                        } else {
                            progress
                        }
                    )
                    .offset(y = (-34).dp)

            )

        },
        state = state,
        modifier = Modifier.fillMaxSize(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
    ) {
        LazyColumn(
            state = lazyColumnState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.car_statistics_label),
                        style = MaterialTheme.typography.titleMedium,
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
            }
            item {
                LazyRow(
                    state = lazyRowState,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(statisticsList) {
                        val model = GetStatisticModel(it)
                        StatisticsRowView(
                            title = model.title,
                            description = model.description,
                            color = model.color,
                            icon = model.icon
                        )
                    }
                }
            }

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.nearby_super_chargers_label),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = stringResource(id = R.string.super_chargers_view_all_label),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }

            items(stationList) {
                StationRowView(stationModel = it)
            }
        }
    }

}

@Composable
fun GetStatisticModel(item: ChargingStatistics): StatisticModel {
    return when (item) {
        is ChargingStatistics.ChargeTime -> {
            StatisticModel(
                title = stringResource(id = R.string.charging_time_label, item.minutes),
                description = stringResource(id = R.string.charging_type_fast_label),
                icon = ImageVector.vectorResource(R.drawable.ic_plug_circle_bolt_solid),
                color = JucrAppTheme.colors.yellow
            )
        }
        is ChargingStatistics.Range -> {
            StatisticModel(
                title = stringResource(id = R.string.range_km_label, item.currentRange),
                description = stringResource(id = R.string.remaining_charge_label),
                icon = ImageVector.vectorResource(R.drawable.ic_battery_three_quarters_solid),
                color = JucrAppTheme.colors.success
            )
        }
        is ChargingStatistics.BatteryHealth -> {
            StatisticModel(
                title = stringResource(id = R.string.battery_volts, item.volts),
                description = stringResource(id = R.string.voltage_label),
                icon = ImageVector.vectorResource(R.drawable.ic_car_battery_solid),
                color = JucrAppTheme.colors.red
            )
        }
    }
}

data class StatisticModel(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color
)


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
            .clip(RoundedCornerShape(16.dp))
            .clickable { },
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
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
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
    color: Color,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clickable { },
        border = BorderStroke(
            width = DividerDefaults.Thickness,
            DividerDefaults.color.copy(alpha = 0.3f)
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .size(120.dp)
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        color = color.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(1)
                    )
                    .scale(0.4f),
                tint = color,
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
            description = "Voltage",
            color = Color.Red,
            icon = Icons.Default.BabyChangingStation
        )
    }
}


@Composable
@Preview(showSystemUi = true)
fun PreviewHomeScreenContent() {
    val statistics = listOf(
        ChargingStatistics.BatteryHealth(240),
        ChargingStatistics.ChargeTime(23),
        ChargingStatistics.Range("100"),
    )
    JucrAppTheme(darkTheme = false) {
        Column {
            HomeScreenContent(
                stationList = getMockedStations(),
                statisticsList = statistics,
                currentCharging = 46
            )
        }
    }
}