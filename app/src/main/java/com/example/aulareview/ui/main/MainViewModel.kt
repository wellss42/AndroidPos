package com.example.aulareview.ui.main

import androidx.lifecycle.*
import com.example.aulareview.domain.mapper.toModel
import com.example.aulareview.domain.model.SampleModel
import com.example.aulareview.domain.repository.impl.SampleRepositoryImpl
import com.example.aulareview.networking.model.SampleResponse
import com.example.aulareview.networking.service.Resource
import com.example.aulareview.ui.model.ErrorModel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = SampleRepositoryImpl()

    private val _sampleData = MutableLiveData<List<SampleModel>>()
    val sampleData: LiveData<List<SampleModel>> = _sampleData

    private val _sampleError = MutableLiveData<ErrorModel>()
    val sampleError: LiveData<ErrorModel> = _sampleError

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    private val callObserver: Observer<Resource<List<SampleResponse>>> =
        Observer { response -> processResponse(response) }
    //setando observador


    private suspend fun callObserverAPI(cep: String) {
        //suspend function que observa se houve alguma mudança
        repository.getSampleData(cep).observeForever { callObserver.onChanged(it) }
    }

    private fun processResponse(response: Resource<List<SampleResponse>>?) {
        //Aqui iremos processar a response de acordo com o retorno na camada de Repository
        when(response?.status) {
            Resource.Status.SUCCESS -> {
                setLoading(false)
                response.data?.let { sampleDataOnSuccess(it) }
            }
            Resource.Status.ERROR -> {
                setLoading(false)
                response.apiError?.let { sampleDataOnError(it) }
            }
            Resource.Status.LOADING -> {
                setLoading(true)
            }
            null -> TODO()
        }
        _sampleData.value = response?.data?.toModel()
    }

    private fun sampleDataOnSuccess(resultResponse: List<SampleResponse>){
        //tratativa de sucesso
        _sampleData.value = resultResponse.toModel()
    }

    private fun sampleDataOnError(message: String){
        //tratativa de erro
        _sampleError.value = ErrorModel(
            title = "Erro na chamada de api",
            message = message,
            errorCode = "0001"
        )
    }

    fun setLoading(isStateLoading: Boolean) {
        //tratativa de loading
        isLoading.value = isStateLoading
    }

    fun getSampleData(cep: String) {
        //realizamos a chamada na camada de repository e adicionamos o observador
        viewModelScope.launch {
            callObserverAPI(cep)
            repository.getSampleData(cep)
        }
    }

}