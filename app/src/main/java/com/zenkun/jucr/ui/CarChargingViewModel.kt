package com.zenkun.jucr.ui

import androidx.lifecycle.ViewModel
import com.zenkun.domain.user.GetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CarChargingViewModel @Inject constructor(
    getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {
}