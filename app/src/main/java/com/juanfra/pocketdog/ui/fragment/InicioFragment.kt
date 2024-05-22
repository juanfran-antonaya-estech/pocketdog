package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.databinding.FragmentInicioBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel


class InicioFragment : Fragment() {
    private lateinit var binding: FragmentInicioBinding

    val viewModels by activityViewModels<PesetasViewModel> {
        PesetasViewModel.PesetasViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}