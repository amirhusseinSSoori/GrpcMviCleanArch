package com.amirhusseinsoori.grpcmviarch.data.repository

import com.amirhusseinsoori.grpcmviarch.data.source.NetworkSource
import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import io.grpc.domain.request.SettingReply
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GrpcRepositoryImp @Inject constructor(var api: NetworkSource) : GrpcRepository {
    override suspend fun apiTurnOn(turnOn: TurnOn): Flow<Result<SettingReply>> = flow {
        emit(Result.success(api.requestTurnOnWithSafe(turnOn)))
    }.catch { ex ->
        emit(Result.failure(ex))
    }.flowOn(Dispatchers.IO)
}