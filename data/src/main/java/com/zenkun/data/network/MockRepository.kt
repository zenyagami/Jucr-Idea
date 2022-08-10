package com.zenkun.data.network

import com.zenkun.data.network.model.ChargingType
import com.zenkun.data.network.model.Statistics
import com.zenkun.data.network.model.UserChargingStateResponse
import com.zenkun.data.network.model.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MockRepository @Inject constructor() : Repository {

    override fun getCarChargingState(): Flow<UserChargingStateResponse> {
        return flow {
            emit(
                UserChargingStateResponse(
                    carName = "Audi RS3",
                    carImageUrl = "",
                    chargingLevel = (0..100).random(),
                    timeToFinishChargeInMillis = TimeUnit.MINUTES.toMillis(
                        (10..59).random().toLong()
                    ),
                    statistics = Statistics(
                        voltage = 240,
                        currentRangeInMeters = (50..5000).random(),
                        chargingType = if ((0..10).random() % 2 == 0) {
                            ChargingType.Ac
                        } else {
                            ChargingType.Dc
                        },
                        chargingTimeInMillis = TimeUnit.MINUTES.toMillis(
                            (10..45).random().toLong()
                        )
                    )
                )
            )
        }
    }

    override fun getUser(): Flow<UserResponse> {
        return flowOf(UserResponse(userName = "Javier"))
    }
}