package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import android.os.Bundle
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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCollectTurnOnRequest()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnGrpcFRequest.setOnClickListener {
            onSubscribeTurnOnRequest()
        }

    }

    private fun onSubscribeTurnOnRequest(){
        lifecycleScope.launch {
            viewModel. handleIntent(TurnOn(androidId,Date().time))
            viewModel.userIntent.send(MainIntent.FetchUser)
        }
    }

    private fun onCollectTurnOnRequest() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainState.Success -> {
                        binding.txtGrpcFStatTimeResult.text=it.user.startTime.toString()
                        binding.txtGrpcFIntervalTimeResult.text=it.user.intervalCon.toString()
                        toasty("Result : Success",1)
                    }
                    is MainState.Error -> {
                        toasty("Result : ${it.error}",3)
                    }

                    else ->  Unit
                }
            }
        }
    }
}