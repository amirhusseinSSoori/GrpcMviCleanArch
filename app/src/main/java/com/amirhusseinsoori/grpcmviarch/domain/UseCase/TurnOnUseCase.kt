package com.amirhusseinsoori.grpcmviarch.domain.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.UseCase.base.UseCase

import com.amirhusseinsoori.grpcmviarch.domain.repository.GrpcRepository
import com.arad.domain.entity.TurnOn
import io.grpc.domain.mizannodes.SettingReply

import javax.inject.Inject

class TurnOnUseCase @Inject constructor(val grpcRepository: GrpcRepository):
    UseCase<TurnOn, SettingReply>() {
    override suspend fun execute(settingReply: TurnOn?): SettingReply {
        return grpcRepository.apiTurnOn(settingReply!!)
    }


}