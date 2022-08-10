package com.zenkun.domain.car.model

data class CarChargingState(
    val statistics: List<ChargingStatistics>,
    val carName: String,
    val chargingLevel: Int,
    val carImageUrl: String,
)