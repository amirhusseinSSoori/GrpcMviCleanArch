package com.amirhusseinsoori.grpcmviarch.domain.repository




import com.arad.domain.entity.TurnOn
import kotlinx.coroutines.flow.Flow
import requestTest.SettingReply


interface GrpcRepository {
    suspend fun apiTurnOn(turnOn: TurnOn): Flow<Result<SettingReply>>
}