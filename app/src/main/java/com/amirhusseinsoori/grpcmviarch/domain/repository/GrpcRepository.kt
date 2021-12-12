package com.amirhusseinsoori.grpcmviarch.domain.repository




import com.arad.domain.entity.TurnOn
import io.grpc.domain.request.SettingReply
import kotlinx.coroutines.flow.Flow


interface GrpcRepository {
    suspend fun apiTurnOn(turnOn: TurnOn): Flow<Result<SettingReply>>
}