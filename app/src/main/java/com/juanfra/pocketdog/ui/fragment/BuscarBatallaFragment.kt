package com.juanfra.pocketdog.ui.fragment

import BuscarBatallaAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.FragmentBuscarBatallaBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BuscarBatallaFragment : Fragment() {
    private lateinit var binding: FragmentBuscarBatallaBinding
    private lateinit var adapter: BuscarBatallaAdapter
    val viewModel = Companion.viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBuscarBatallaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        //llamar a una corrutina para actualizar la lista
        var grupos : MutableLiveData<List<DogTrio>> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            grupos.postValue(viewModel.getDogTrios(arrayListOf("muy facil", "muy facil")))
        }
        grupos.observe(viewLifecycleOwner) {
            adapter.actualizarLista(ArrayList(it))
        }
    }

    fun setupAdapter() {
        adapter = BuscarBatallaAdapter(ArrayList(), object : BuscarBatallaAdapter.OnButtonPressed {
            override fun onButtonPressed(enemigos: DogTrio) {
                viewModel.battleTrio(enemigos)
                findNavController().navigate(R.id.action_buscarBatallaFragment_to_batallaFragment)
            }
        })
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvBB.adapter = adapter
        binding.rvBB.layoutManager = layoutManager
    }

    companion object {
        lateinit var viewModel: PesetasViewModel
    }
}