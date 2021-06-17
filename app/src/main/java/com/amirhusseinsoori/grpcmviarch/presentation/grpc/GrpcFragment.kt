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
import com.arad.domain.entity.TurnOn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class GrpcFragment :Fragment(R.layout.fragment_grpc) {

    private val viewModel: GrpcViewModel by viewModels()
    @Inject
    lateinit var androidId: String

    lateinit var binding:FragmentGrpcBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCollectTurnOnRequest()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding=FragmentGrpcBinding.bind(view)
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


                        Log.e("TAG", "onCollectTurnOnRequest: ${even.data}", )


                    }
                    is GrpcWrapper.GrpcError -> {

                        Log.e("TAG", "onCollectTurnOnRequest: ${even.error}", )

                    }
                    is GrpcWrapper.UnknownError -> {

                        Log.e("TAG", "onCollectTurnOnRequest: ${even.error}", )
                    }

                    else -> Unit
                }
            }


        }
    }
}