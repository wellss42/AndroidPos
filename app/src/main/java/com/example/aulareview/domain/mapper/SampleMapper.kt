package com.example.aulareview.domain.mapper

import com.example.aulareview.domain.model.SampleModel
import com.example.aulareview.networking.model.SampleResponse


//Mapeia a response para o modelo da camada de Domain
fun List<SampleResponse>.toModel(): List<SampleModel>{
    return this.map { item->
        SampleModel(
            cep = item.cep.orEmpty(),
            logradouro = item.logradouro.orEmpty(),
            complemento = item.complemento.orEmpty(),
            bairro = item.bairro.orEmpty(),
            localidade = item.localidade.orEmpty(),
            uf = item.uf.orEmpty(),
            ibge = item.ibge.orEmpty(),
            gia = item.gia.orEmpty(),
            ddd = item.ddd.orEmpty(),
            siafi = item.siafi.orEmpty()
        )
    }
}