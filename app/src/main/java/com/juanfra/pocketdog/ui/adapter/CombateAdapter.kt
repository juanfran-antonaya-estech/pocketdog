package com.juanfra.pocketdog.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.databinding.HolderRegistroBinding


class CombateAdapter(private var listado: ArrayList<Resultado>): RecyclerView.Adapter<CombateAdapter.MiHolder>() {

    inner class MiHolder(var binding: HolderRegistroBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderRegistroBinding.inflate(layoutInflater, parent, false)

        return MiHolder(binding)
    }

    override fun onBindViewHolder(holder: MiHolder, position: Int) {
        val enfrentamiento = listado[position]
        holder.binding.apply {
            doggoaliado// Asegúrate de tener estos campos en tu HolderRegistroBinding
            doggoenemigo
            resultado.text = enfrentamiento.resultado.toString()
        }
    }

    override fun getItemCount(): Int {
        return listado.size
    }
    fun updateList(newList: ArrayList<Resultado>){
        listado = newList
        notifyDataSetChanged()
    }
}