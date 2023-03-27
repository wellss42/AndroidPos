package com.example.aulareview.domain.repository.impl

import androidx.lifecycle.LiveData
import com.example.aulareview.networking.SampleNetworking
import com.example.aulareview.networking.model.SampleResponse
import com.example.aulareview.networking.service.NetworkCall
import com.example.aulareview.networking.service.Resource
import com.example.aulareview.networking.service.RetrofitClient

class SampleRepositoryImpl {

    //cria a instancia do retrofit com a classe de networking
    private val remote = RetrofitClient.createPostService(SampleNetworking::class.java)

    //função que sobreescreve a função de interface realizando a chamada no retrofit
    fun getSampleData(cep: String): LiveData<Resource<List<SampleResponse>>> {
        val getSampleCall = NetworkCall<List<SampleResponse>>()
        return getSampleCall.makeCall(remote.getSampleData(cep))
    }
}