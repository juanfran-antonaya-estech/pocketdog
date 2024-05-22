package com.juanfra.pocketdog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.databinding.HolderTiendaBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import com.squareup.picasso.Picasso

class TiendaAdapter(var listado: ArrayList<Doggo>) :
    RecyclerView.Adapter<TiendaAdapter.MiCelda>() {

    inner class MiCelda(var binding: HolderTiendaBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderTiendaBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val doggo = listado[position]


        with(holder){
            binding.nameDoggo.text = doggo.refdog.breeds[0].name
            binding.rareDoggo.text = doggo.rarity

            binding.btPaid.setOnClickListener {
                Toast.makeText(holder.itemView.context, "PROCESANDO PAGO", Toast.LENGTH_SHORT).show()
                Toast.makeText(holder.itemView.context, "¡Error! No se pudo procesar el pago", Toast.LENGTH_SHORT).show()
            }

        }

        Picasso.get()
            .load(doggo.refdog.url)
            .into(holder.binding.imageDoggo)

    }

    fun updateList(newList: ArrayList<Doggo>){
        listado = newList
        notifyDataSetChanged()
    }



}