package com.juanfra.pocketdog.ui.adapter

import android.database.DataSetObserver
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.HolderMisPerrosBinding
import com.juanfra.pocketdog.databinding.HolderTresperrosBinding
import com.juanfra.pocketdog.ui.fragment.MisPerrosFragment

class InventarioAdapter(var listado: ArrayList<DogTrio>, val listener: AdapterView.OnItemClickListener) :
    RecyclerView.Adapter<InventarioAdapter.MyHolder>() {
    open var rarity = "Comun"

    interface OnItemClickListener {
        fun itemClick(url: String)
    }

    inner class MyHolder(val binding: HolderMisPerrosBinding ) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderMisPerrosBinding.inflate(layoutInflater, parent, false)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        val dogtrio = listado[position]

        with(holder) {
            binding.nombreUno.text = dogtrio.packName
            binding.rarezaUno.text= dogtrio.packLevel
        }


        val packLevelColors = mapOf(
            "Muy Fácil" to R.color.veryeasy,
            "Fácil" to R.color.easy,
            "Normal" to R.color.medium,
            "Difícil" to R.color.hard,
            "Muy Difícil" to R.color.veryhard
        )

        when (dogtrio.packLevel) {
            in packLevelColors -> {
                val color = Integer.toHexString(holder.itemView.context.getColor(packLevelColors[dogtrio.packLevel]!!))
            }
        }
    }




    override fun getItemCount(): Int {
        return listado.size    }

    fun actualizarLista(list: ArrayList<DogTrio>) {
        listado = list
        notifyDataSetChanged()
    }
}


