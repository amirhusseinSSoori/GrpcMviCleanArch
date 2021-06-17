package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.grpcmviarch.domain.UseCase.TurnOnUseCase
import com.amirhusseinsoori.grpcmviarch.domain.exception.GrpcWrapper
import com.arad.domain.entity.TurnOn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.domain.mizannodes.SettingReply
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class GrpcViewModel @Inject constructor(
    var turnOnUseCase: TurnOnUseCase,
) : ViewModel() {

    //for turnRequestInfo
    var getTurnOn = MutableStateFlow<GrpcWrapper<SettingReply>>(GrpcWrapper.Empty())
    val getTurnOnCollect: StateFlow<GrpcWrapper<SettingReply>> = getTurnOn





    fun turnOnRequest(turnOn: TurnOn) {
        viewModelScope.launch {
            getTurnOn.value = turnOnUseCase.execute(turnOn)
        }
    }
}