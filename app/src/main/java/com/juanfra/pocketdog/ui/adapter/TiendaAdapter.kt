package com.juanfra.pocketdog.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.databinding.HolderTiendaBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import com.squareup.picasso.Picasso

class TiendaAdapter(var listado: ArrayList<Doggo>, val listener: MyClickListener) :
    RecyclerView.Adapter<TiendaAdapter.MiCelda>() {

    inner class MiCelda(var binding: HolderTiendaBinding):
        RecyclerView.ViewHolder(binding.root)

    interface MyClickListener {
        fun onClick(doggo: Doggo)
    }

    var price = 0

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

            if (doggo.rarity == "Comun" && doggo.rarity == "Común"){
                price = 200
                binding.btPaid.text = "$price ptas."
                holder.binding.bkTienda.setBackgroundColor(R.drawable.shape_tienda_normalito)
                holder.binding.rareDoggo.setTextColor(Color.parseColor("#1AFF00"))
            } else if (doggo.rarity == "Raro"){
                price = 400
                binding.btPaid.text = "$price ptas."
                holder.binding.bkTienda.setBackgroundColor(R.drawable.shape_tienda_raro)
                holder.binding.rareDoggo.setTextColor(Color.parseColor("#0048FF"))
            } else if (doggo.rarity == "Epico" && doggo.rarity == "Épico") {
                price = 800
                binding.btPaid.text = "$price ptas."
                holder.binding.bkTienda.setBackgroundColor(R.drawable.shape_tienda_epico)
                holder.binding.rareDoggo.setTextColor(Color.parseColor("#A600FF"))
            } else {
                price = 1600
                binding.btPaid.text = "$price ptas."
                holder.binding.bkTienda.setBackgroundColor(R.drawable.shape_tienda_legendario)
                holder.binding.rareDoggo.setTextColor(Color.parseColor("#FFF200"))
            }

            binding.btPaid.setOnClickListener {
                listener.onClick(doggo)
                binding.btPaid.isEnabled = false
            }

        }

        Picasso.get()
            .load(doggo.refdog.url)
            .into(holder.binding.imageDoggo)

    }

    fun price(doggo: Doggo){
        if (doggo.rarity == "Comun" || doggo.rarity == "Común"){
            price = 200
        } else if (doggo.rarity == "Raro"){
            price = 400
        } else if (doggo.rarity == "Epico" && doggo.rarity == "Épico") {
            price = 800
        } else {
            price = 1600
        }
    }

    fun updateList(newList: ArrayList<Doggo>){
        listado = newList
        notifyDataSetChanged()
    }



}