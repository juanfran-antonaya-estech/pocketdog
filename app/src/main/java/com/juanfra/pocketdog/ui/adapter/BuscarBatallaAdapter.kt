import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.HolderTresperrosBinding
import com.squareup.picasso.Picasso

class BuscarBatallaAdapter(var listado: ArrayList<DogTrio>, val listener: OnButtonPressed) :
    RecyclerView.Adapter<BuscarBatallaAdapter.MiCelda>() {

    interface OnButtonPressed {
        fun onButtonPressed(enemigos: DogTrio)
    }

    //Your holder here
    inner class MiCelda(var binding: HolderTresperrosBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderTresperrosBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val dogtrio = listado[position]

        with(holder) {
            binding.tvPacknameHolder.text = dogtrio.packName
            binding.tvPackLevelHolder.text = dogtrio.packLevel
            binding.btAccionHolder.setOnClickListener {
                listener.onButtonPressed(dogtrio)
            }
        }
        Picasso.get()
            .load(dogtrio.perros.get(0).refdog.url)
            .into(holder.binding.ivPerro1Holder)
        Picasso.get()
            .load(dogtrio.perros.get(1).refdog.url)
            .into(holder.binding.ivPerro2Holder)
        Picasso.get()
            .load(dogtrio.perros.get(2).refdog.url)
            .into(holder.binding.ivPerro3Holder)

        when (dogtrio.packLevel) {
            "Muy Fácil" -> {
                holder.binding.root.setStrokeColor(Resources.getSystem().getColor(R.color.veryeasy))
                holder.binding.tvPackLevelHolder.setTextColor(Resources.getSystem().getColor(R.color.veryeasy))
            }
            "Fácil" -> {
                holder.binding.root.setStrokeColor(Resources.getSystem().getColor(R.color.easy))
                holder.binding.tvPackLevelHolder.setTextColor(Resources.getSystem().getColor(R.color.easy))
            }
            "Normal" -> {
                holder.binding.root.setStrokeColor(Resources.getSystem().getColor(R.color.medium))
                holder.binding.tvPackLevelHolder.setTextColor(Resources.getSystem().getColor(R.color.medium))
            }
            "Difícil" -> {
                holder.binding.root.setStrokeColor(Resources.getSystem().getColor(R.color.hard))
                holder.binding.tvPackLevelHolder.setTextColor(Resources.getSystem().getColor(R.color.hard))
            }
            "Muy Difícil" -> {
                holder.binding.root.setStrokeColor(Resources.getSystem().getColor(R.color.veryhard))
                holder.binding.tvPackLevelHolder.setTextColor(Resources.getSystem().getColor(R.color.veryhard))
            }
        }
    }

    fun actualizarLista(list: ArrayList<DogTrio>) {
        listado = list
        notifyDataSetChanged()
    }


}