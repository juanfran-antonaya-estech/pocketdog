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
        fun onClick(doggo: Doggo) // Pasamos el doggo a la llamada del botón
    }


    // Se crea un mapOf para asignar el precio de cada rareza a un valor entero
    var price = mapOf("Comun" to 200, "Raro" to 400, "Épico" to 600, "Legendario" to 1600)

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


        // Se accede a los elementos del holder para asignar los valores básicos del Doggo a comprar
        with(holder){
            binding.nameDoggo.text = doggo.refdog.breeds[0].name
            binding.rareDoggo.text = doggo.rarity
            binding.btPaid.setOnClickListener {
                listener.onClick(doggo)
                binding.btPaid.isEnabled = false // Una vez echo click, se deshabilita el botón (dando igual si tienes o no dinero o si ya tienes completo tu inventario 3/3)
            }

            binding.btPaid.text = price[doggo.rarity]!!.toString() + " ptas."

            // Igual como el precio, se cambia el color del card por la rareza
            val colorCard = mapOf(
                "Comun" to R.drawable.shape_tienda_normalito,
                "Raro" to R.drawable.shape_tienda_raro,
                "Épico" to R.drawable.shape_tienda_epico,
                "Legendario" to R.drawable.shape_tienda_legendario
            )
            // Se cambia el color del texto por la rareza
            val colorText = mapOf(
                "Comun" to Color.GREEN,
                "Raro" to Color.BLUE,
                "Épico" to Color.MAGENTA,
                "Legendario" to Color.YELLOW
            )

            binding.bkTienda.setBackgroundResource(colorCard[doggo.rarity]!!)
            binding.rareDoggo.setTextColor(colorText[doggo.rarity]!!)
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