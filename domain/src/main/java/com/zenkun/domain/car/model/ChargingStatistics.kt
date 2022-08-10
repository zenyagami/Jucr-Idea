package com.zenkun.domain.car.model


sealed class ChargingStatistics {
    data class BatteryHealth(val volts: Int) : ChargingStatistics()
    data class Range(val currentRange: String) : ChargingStatistics()
    data class ChargeTime(val minutes: Long) : ChargingStatistics()
}