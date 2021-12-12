package com.amirhusseinsoori.grpcmviarch.domain.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.UseCase.base.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import io.grpc.domain.request.SettingReply
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class TurnOnUseCase @Inject constructor(val grpcRepository: GrpcRepository):
    UseCase<TurnOn, Flow<Result<SettingReply>>>() {
    override suspend fun execute(params: TurnOn?): Flow<Result<SettingReply>> {
        return grpcRepository.apiTurnOn(params!!)
    }


}