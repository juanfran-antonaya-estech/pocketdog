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
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.FragmentMisPerrosBinding
import com.juanfra.pocketdog.databinding.FragmentTiendaBinding
import com.juanfra.pocketdog.ui.adapter.InventarioAdapter
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
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


        viewModel.yourtrio.observe(viewLifecycleOwner, Observer { dogTrio ->
            dogTrio?.let {
                val list = arrayListOf(dogTrio)
                adapter.actualizarLista(list)
            }
        })
        viewModel.loadDoggos()
    }


    fun setupAdapter(){
        adapter = InventarioAdapter(ArrayList(), object : InventarioAdapter.MyClick {
            override fun onClick(dogTrio: DogTrio) {
                Toast.makeText(requireContext(), "ohhh", Toast.LENGTH_SHORT).show()
            }

        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}



