package com.juanfra.pocketdog.ui.fragment

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import androidx.navigation.fragment.findNavController
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
=======
import androidx.fragment.app.activityViewModels
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel


class InicioFragment : Fragment() {

    val viewModels by activityViewModels<PesetasViewModel> {
        PesetasViewModel.PesetasViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

>>>>>>> origin/rama-juanfran
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.barraInventario.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_misPerrosFragment)
        }
        binding.barraBatalla.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_misPerrosFragment)
        }
        binding.barraTienda.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_misPerrosFragment)
        }
    }


}