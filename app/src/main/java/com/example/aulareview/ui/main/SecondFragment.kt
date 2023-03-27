package com.example.aulareview.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.aulareview.R
import com.example.aulareview.databinding.FragmentMainBinding
import com.example.aulareview.databinding.FragmentSecondBinding
import com.example.aulareview.domain.model.SampleModel
import com.example.aulareview.domain.repository.impl.SampleRepositoryImpl
import com.example.aulareview.networking.model.SampleResponse
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment: Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        binding.retornar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_MainFragment)
        }
    }

    private fun setObserver() {
        viewModel.sampleData.observe(viewLifecycleOwner
        ) {
            Log.e("Tentando Acessar o cep", viewModel.sampleData.value?.get(0)?.cep.toString())
            Log.e("Quase deu", "to aqui dentro")
            binding.logradouro.text = viewModel.sampleData.value?.get(0)?.cep
        }
    }
}