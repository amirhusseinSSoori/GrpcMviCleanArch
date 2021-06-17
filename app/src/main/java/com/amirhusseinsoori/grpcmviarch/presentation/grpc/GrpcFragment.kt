package com.amirhusseinsoori.grpcmviarch.presentation.grpc

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.amirhusseinsoori.grpcmviarch.databinding.FragmentGrpcBinding
import com.amirhusseinsoori.grpcmviarch.presentation.base.BaseFragment
import com.arad.domain.entity.TurnOn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class GrpcFragment :  BaseFragment<FragmentGrpcBinding>(FragmentGrpcBinding::inflate) {

    private val viewModel: GrpcViewModel by viewModels()
    @Inject
    lateinit var androidId: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGrpcFRequest.setOnClickListener {
            onCollectTurnOnRequest()
            CoroutineScope(Dispatchers.IO).launch() {
                viewModel.userIntent.send(MainIntent.FetchTurnOn(TurnOn(androidId, Date().time)))

            }

        }
    }


    private fun onCollectTurnOnRequest() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {

                    is MainState.Success -> {
                       binding.txtGrpcFIntervalTimeResult.text = it.settingReply.intervalCon.toString()
                        binding.txtGrpcFStatTimeResult.text = it.settingReply.startTime.toString()
                        toasty("Success",1)
                    }

                    is MainState.Error -> {
                        toasty("Error",3)
                    }

                    else -> Unit
                }
            }

        }

    }




    }
