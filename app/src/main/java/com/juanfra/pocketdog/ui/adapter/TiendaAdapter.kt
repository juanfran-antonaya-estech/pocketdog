package com.juanfra.pocketdog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.databinding.HolderTiendaBinding

class TiendaAdapter(var listado: ArrayList<Doggo>) :
    RecyclerView.Adapter<TiendaAdapter.MiCelda>() {

        inner class MiCelda(var binding: HolderTiendaBinding):
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
            val binding = HolderTiendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MiCelda(binding)
        }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val doggo = listado[position]

        with(holder){
            binding.nameDoggo.text = doggo.rarity
        }
    }

    fun updateList(newList: ArrayList<Doggo>){
        listado = newList
        notifyDataSetChanged()
    }



}