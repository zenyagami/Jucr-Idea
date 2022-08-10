package com.zenkun.domain.car

import com.zenkun.data.network.Repository
import com.zenkun.domain.car.model.CarChargingState
import com.zenkun.domain.car.model.ChargingStatistics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GetCarChargingStateUseCase @Inject constructor(private val repository: Repository) {

    fun execute(): Flow<CarChargingState> {
        return repository.getCarChargingState()
            .map {

                CarChargingState(
                    statistics = listOf(
                        ChargingStatistics.BatteryHealth(it.statistics.voltage),
                        ChargingStatistics.Range(
                            currentRange = String.format(
                                Locale.getDefault(),
                                "%.1f",
                                it.statistics.currentRangeInMeters / METERS_IN_KM
                            )
                        ),
                        ChargingStatistics.ChargeTime(
                            TimeUnit.MILLISECONDS.toMinutes(it.statistics.chargingTimeInMillis)
                        )
                    ),
                    carName = it.carName,
                    chargingLevel = it.chargingLevel,
                    carImageUrl = it.carImageUrl
                )
            }
    }

    private companion object {
        private const val METERS_IN_KM = 1000
    }
}