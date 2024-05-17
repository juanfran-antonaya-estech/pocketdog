import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.HolderTresperrosBinding

class BuscarBatallaAdapter(var listado: ArrayList<DogTrio>) :
    RecyclerView.Adapter<BuscarBatallaAdapter.MiCelda>() {
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

    }


}