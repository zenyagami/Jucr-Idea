package com.zenkun.data.network

import com.zenkun.data.network.model.UserChargingStateResponse
import com.zenkun.data.network.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUser(): Flow<UserResponse>
    fun getCarChargingState(): Flow<UserChargingStateResponse>
}