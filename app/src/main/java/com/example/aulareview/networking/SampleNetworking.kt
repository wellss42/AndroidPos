package com.example.aulareview.networking

import com.example.aulareview.networking.model.SampleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SampleNetworking {

    //Método http com o endpoint da chamada e o tipo de retorno esperado
    @GET("{cep}/json/")
    fun getSampleData(@Path("cep")cep: String): Call<List<SampleResponse>>
}