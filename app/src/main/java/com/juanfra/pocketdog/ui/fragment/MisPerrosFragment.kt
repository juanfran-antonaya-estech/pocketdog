package com.juanfra.pocketdog.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.databinding.FragmentMisPerrosBinding
import com.juanfra.pocketdog.databinding.FragmentTiendaBinding
import com.juanfra.pocketdog.ui.adapter.InventarioAdapter
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList


class MisPerrosFragment : Fragment() {

    private lateinit var binding : FragmentMisPerrosBinding
    private lateinit var adapter: InventarioAdapter
    private val viewModel by activityViewModels<PesetasViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMisPerrosBinding.inflate(inflater, container, false)
        return binding.root
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaPlayer = MediaPlayer.create(context, R.raw.pdtienda3)
        mediaPlayer.start()
        setupAdapter()

        val miTrio: MutableLiveData<List<DogTrio>> = MutableLiveData()


        viewModel.yourtrio.observe(viewLifecycleOwner, Observer { dogTrio ->
            dogTrio?.let {

                adapter.actualizarLista(ArrayList(it.perros))
            }
        })
    }


    fun setupAdapter(){
        adapter = InventarioAdapter(ArrayList(), object : InventarioAdapter.MyClick {

            override fun onClick(doggo: Doggo) {
                Toast.makeText(requireContext(), "s", Toast.LENGTH_SHORT).show()
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}



