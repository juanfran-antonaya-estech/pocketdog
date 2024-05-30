package com.juanfra.pocketdog.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.databinding.FragmentInicioBinding
import com.juanfra.pocketdog.databinding.HolderRegistroBinding
import com.juanfra.pocketdog.databinding.HolderTiendaBinding
import com.squareup.picasso.Picasso

class CombateAdapter(var listado: ArrayList<Resultado>): RecyclerView.Adapter<CombateAdapter.MiHolder>(){
    private lateinit var binding: HolderRegistroBinding
    inner class MiHolder(var binding: HolderRegistroBinding):
        RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CombateAdapter.MiHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderRegistroBinding.inflate(layoutInflater, parent, false)
        return MiHolder(binding)
    }

    override fun onBindViewHolder(holder: CombateAdapter.MiHolder, position: Int) {
        val enfrentamiento = listado[position]
        binding.doggoaliado
        Picasso.get().load(enfrentamiento.urldog1).into(binding.doggoaliado)
        Picasso.get().load(enfrentamiento.urldog2).into(binding.doggoenemigo)
        binding.doggoenemigo
        binding.resultado.text = "victoria"
    }

    override fun getItemCount(): Int {
        return listado.size
    }


}