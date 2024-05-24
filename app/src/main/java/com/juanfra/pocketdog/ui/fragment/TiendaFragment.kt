package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.pesetas.Pesetas
import com.juanfra.pocketdog.databinding.FragmentTiendaBinding
import com.juanfra.pocketdog.ui.adapter.TiendaAdapter
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TiendaFragment : Fragment() {
    private lateinit var binding: FragmentTiendaBinding
    private lateinit var adapter: TiendaAdapter
    private val viewModel by activityViewModels<PesetasViewModel>()


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

        setupAdapter()

        var doggos : MutableLiveData<List<Doggo>> = MutableLiveData()

        CoroutineScope(Dispatchers.IO).launch {
            doggos.postValue(arrayListOf(viewModel.getRandomDoggo("comun"), (viewModel.getRandomDoggo("raro")), (viewModel.getRandomDoggo("epico")), (viewModel.getRandomDoggo("legendario"))))

        }
        doggos.observe(viewLifecycleOwner) {
            adapter.updateList(ArrayList(it))
        }

        viewModel.pesetas.observe(viewLifecycleOwner) {
            binding.ptasActuales.text = it.pesetas.toString() + " ptas."
        }



    }

    fun setupAdapter() {
        adapter = TiendaAdapter(ArrayList(), object : TiendaAdapter.MyClickListener {
            override fun onClick(doggo: Doggo) {
                viewModel.buyDoggo(doggo.refdog.id, adapter.price)
            }
        })
        binding.rvTienda.adapter = adapter
        binding.rvTienda.layoutManager = LinearLayoutManager(requireContext())
    }

}