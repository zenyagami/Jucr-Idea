package com.zenkun.domain.user

import com.zenkun.data.network.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(private val repository: Repository) {

    fun execute(): Flow<String> {
        return repository.getUser()
            .map { it.userName }
    }
}