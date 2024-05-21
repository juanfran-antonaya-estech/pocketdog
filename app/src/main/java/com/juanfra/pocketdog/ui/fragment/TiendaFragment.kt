package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.databinding.FragmentTiendaBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel

class TiendaFragment : Fragment() {
    private lateinit var binding: FragmentTiendaBinding
    private lateinit var ptasViewModel: PesetasViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTiendaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ptasViewModel = ViewModelProvider(this).get(PesetasViewModel::class.java)
        if (ptasViewModel.getPesetas() == null) {
            ptasViewModel.setPesetas()
        }



        Toast.makeText(requireContext(), ptasViewModel.getPesetas().toString(), Toast.LENGTH_SHORT).show()


        binding.ptasActuales.text = ptasViewModel.getPesetas().toString()+ " ptas."

        binding.testRestar.setOnClickListener {
            ptasViewModel.minusPesetas(1)
            binding.ptasActuales.text = ptasViewModel.getPesetas().toString()+ " ptas."
            Toast.makeText(requireContext(), ptasViewModel.getPesetas().toString(), Toast.LENGTH_SHORT).show()
        }

    }
}