package com.amirhusseinsoori.grpcmviarch.data.repository

import com.amirhusseinsoori.grpcmviarch.data.source.NetworkSource
import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import requestTest.SettingReply
import javax.inject.Inject

class GrpcRepositoryImp @Inject constructor(var api: NetworkSource) : GrpcRepository {
    @FlowPreview
    override suspend fun apiTurnOn(turnOn: TurnOn): Flow<Result<SettingReply>> = flow {
        emit(Result.success(api.requestTurnOnWithSafe(turnOn)))
    }.catch { ex ->
        emit(Result.failure(ex))
    }.flowOn(Dispatchers.IO) .debounce(4000).distinctUntilChanged()
}