package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.juanfra.pocketdog.data.models.combate.Resultado

import com.juanfra.pocketdog.databinding.FragmentRegistroBatallasBinding

import com.juanfra.pocketdog.ui.MainActivity
import com.juanfra.pocketdog.ui.adapter.CombateAdapter

import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel

class RegistroBatallasFragment : Fragment() {
    val registro = MutableLiveData<List<Resultado>>()
    private lateinit var adapter: CombateAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel by activityViewModels<PesetasViewModel>()
    private var _binding: FragmentRegistroBatallasBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentRegistroBatallasBinding.inflate(inflater, container, false)

        // Configura el RecyclerView
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        configRecycler()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecycler()
        viewModel.getLog().observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        // Cambiar el título de la Toolbar
        (activity as? MainActivity)?.setToolbarTitle("Log")
    }

    private fun configRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = CombateAdapter(ArrayList()) {

        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        // Aquí puedes cargar la lista inicial, si ya tienes los datos disponibles
        // combateAdapter.updateList(initialData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}