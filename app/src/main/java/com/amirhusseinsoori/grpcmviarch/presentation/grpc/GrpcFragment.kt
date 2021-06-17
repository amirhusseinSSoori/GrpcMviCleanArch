package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.amirhusseinsoori.grpcmviarch.R
import com.amirhusseinsoori.grpcmviarch.databinding.FragmentGrpcBinding
import com.amirhusseinsoori.grpcmviarch.domain.exception.GrpcWrapper
import com.amirhusseinsoori.grpcmviarch.presentation.base.BaseFragment
import com.arad.domain.entity.TurnOn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
        viewModel.turnOnRequest( TurnOn(
            androidId,
            Date().time
        )
        )
    }
    private fun onCollectTurnOnRequest() {
        lifecycleScope.launchWhenStarted {
            viewModel.getTurnOnCollect.collect { even ->

                when (even) {
                    is GrpcWrapper.Success -> {
                        binding.txtGrpcFStatTimeResult.text=even.data!!.startTime.toString()
                        binding.txtGrpcFIntervalTimeResult.text=even.data!!.intervalCon.toString()
                        toasty("Result : Success",1)


                    }
                    is GrpcWrapper.GrpcError -> {

                        toasty("Result : ${even.error}",3)

                    }
                    is GrpcWrapper.UnknownError -> {
                        toasty("Result : ${even.error}",3)

                    }

                    else -> Unit
                }
            }


        }
    }
}