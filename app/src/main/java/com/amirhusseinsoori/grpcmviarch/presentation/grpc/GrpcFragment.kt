package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.amirhusseinsoori.grpcmviarch.databinding.FragmentGrpcBinding
import com.amirhusseinsoori.grpcmviarch.presentation.base.BaseFragment
import com.arad.domain.entity.TurnOn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class GrpcFragment :  BaseFragment<FragmentGrpcBinding>(FragmentGrpcBinding::inflate) {

    private val viewModel: GrpcViewModel by viewModels()
    @Inject
    lateinit var androidId: String


    override fun sideEffect() {
        onCollectTurnOnRequest()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGrpcFRequest.setOnClickListener {
            viewModel.setEvent(GrpcContract.Event.OnShowResult(TurnOn(androidId,Date().time)))
            viewModel.setEvent(GrpcContract.Event.HandelError)
        }
    }


    private fun onCollectTurnOnRequest() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it.state) {
                    is GrpcContract.SendRequestState.Idle -> {
                        Log.e("TAG", "onCollectTurnOnRequest:  isIdle", )}
                    is GrpcContract.SendRequestState.Success -> {
                        binding.txtGrpcFStatTimeResult.text=it.state.settingReply.startTime.toString()
                        binding.txtGrpcFIntervalTimeResult.text=it.state.settingReply.intervalCon.toString()
                    }

                }
            }




        }
        lifecycleScope.launchWhenCreated {

            viewModel.effect.collect {
                when (it) {
                    is GrpcContract.Effect.ShowMessage -> {
                        toasty(it.message,3)
                    }
                }
            }
        }
        }



}
