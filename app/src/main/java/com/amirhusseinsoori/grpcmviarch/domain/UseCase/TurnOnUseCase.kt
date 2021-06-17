package com.amirhusseinsoori.grpcmviarch.domain.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.UseCase.base.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import io.grpc.domain.request.SettingReply

import javax.inject.Inject

class TurnOnUseCase @Inject constructor(val grpcRepository: GrpcRepository):
    UseCase<TurnOn, SettingReply>() {
    override suspend fun execute(params: TurnOn?): SettingReply {
        return grpcRepository.apiTurnOn(params!!)
    }


}