package com.juanfra.pocketdog.ui.adapter

import android.database.DataSetObserver
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.databinding.HolderMisPerrosBinding
import com.juanfra.pocketdog.databinding.HolderTresperrosBinding
import com.juanfra.pocketdog.ui.fragment.MisPerrosFragment
import com.squareup.picasso.Picasso

class InventarioAdapter(var listado: ArrayList<Doggo>, val listener: MyClick) :
    RecyclerView.Adapter<InventarioAdapter.MyHolder>() {

    inner class MyHolder(val binding: HolderMisPerrosBinding) : RecyclerView.ViewHolder(binding.root)


    interface MyClick {
        fun onClick(doggo: Doggo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderMisPerrosBinding.inflate(layoutInflater, parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val dogtrio = listado[position]

        holder.binding.nombreUno.text = dogtrio.refdog.breeds[0].name
        holder.binding.rarezaUno.text = dogtrio.rarity



        val packLevelColors = mapOf(
            "Muy Fácil" to R.color.veryeasy,
            "Fácil" to R.color.easy,
            "Normal" to R.color.medium,
            "Difícil" to R.color.hard,
            "Muy Difícil" to R.color.veryhard
        )

        packLevelColors[dogtrio.rarity]?.let { colorResId ->
            val color = holder.itemView.context.getColor(colorResId)
            holder.binding.rarezaUno.setTextColor(color)
        }
        Picasso.get()
            .load(dogtrio.refdog.url)
            .into(holder.binding.perroUno)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    fun actualizarLista(list: ArrayList<Doggo>) {
        listado = list
        notifyDataSetChanged()
    }
}







