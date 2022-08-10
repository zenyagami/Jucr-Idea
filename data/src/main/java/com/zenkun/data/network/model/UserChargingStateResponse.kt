package com.zenkun.data.network.model

data class UserChargingStateResponse(
    val carName: String,
    val carImageUrl: String,
    val chargingLevel: Int,
    val timeToFinishChargeInMillis: Long,
    val statistics: Statistics,
)

data class Statistics(
    val voltage: Int,
    val chargingTimeInMillis: Long,
    val currentRangeInMeters: Int,
    val chargingType: ChargingType,
)

sealed class ChargingType {
    object Ac : ChargingType()
    object Dc : ChargingType()
}