package com.juanfra.pocketdog.ui.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.databinding.HolderRegistroBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation

class CombateAdapter(
    private var listado: ArrayList<Resultado>,
    private val onMovieClick: (Resultado) -> Unit
) : RecyclerView.Adapter<CombateAdapter.MiHolder>() {

    inner class MiHolder(var binding: HolderRegistroBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderRegistroBinding.inflate(layoutInflater, parent, false)
        return MiHolder(binding)
    }

    override fun onBindViewHolder(holder: MiHolder, position: Int) {
        val list = listado[position]
        with(holder.binding) {
            if (list.resultado) {
                resultado.text = "Victoria"
                Picasso.get()
                    .load(list.urldog1)
                    .into(doggoaliado) // Imagen de carga
                Picasso.get()
                    .load(list.urldog2)
                    .transform(GrayscaleTransformation())
                    .into(doggoenemigo)
                val color = Integer.toHexString(holder.itemView.context.getColor(R.color.green))
                resultado.setTextColor(Color.parseColor("#$color"))
            } else {
                resultado.text = "Derrota"
                Picasso.get()
                    .load(list.urldog1)
                    .transform(GrayscaleTransformation())
                    .into(doggoaliado) // Imagen de carga
                Picasso.get()
                    .load(list.urldog2)
                    .into(doggoenemigo)
                val color = Integer.toHexString(holder.itemView.context.getColor(R.color.red))
                resultado.setTextColor(Color.parseColor("#$color"))
            }


        }

    }

    override fun getItemCount(): Int {
        return listado.size
    }

    fun updateList(newList: List<Resultado>) {
        listado.clear()
        listado.addAll(newList)
        notifyDataSetChanged()
    }
}
