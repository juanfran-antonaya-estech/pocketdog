package com.juanfra.pocketdog.ui.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.pocketdog.data.Repository
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.databinding.FragmentTiendaBinding
import com.juanfra.pocketdog.ui.adapter.TiendaAdapter
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TiendaFragment : Fragment() {
    private lateinit var binding: FragmentTiendaBinding
    private lateinit var adapter: TiendaAdapter
    lateinit var repository : Repository
    private val viewModel by activityViewModels<PesetasViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTiendaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter() // Llamo a la función para configurar el adaptador

        var doggos: MutableLiveData<List<Doggo>> =
            MutableLiveData() // Creo una variable mutable para almacenar los doggos

        // Creo un corrutina para obtener los doggos de la API y actualizo la lista en el adaptador mediante su raza
        CoroutineScope(Dispatchers.IO).launch {
            doggos.postValue(
                arrayListOf(
                    viewModel.getRandomDoggo("comun"),
                    (viewModel.getRandomDoggo("raro")),
                    (viewModel.getRandomDoggo("epico")),
                    (viewModel.getRandomDoggo("legendario"))
                )
            )
        }
        // Observo los doggos y actualizo la lista en el adaptador
        doggos.observe(viewLifecycleOwner) {
            adapter.updateList(ArrayList(it))
        }
        // Observo las pesetas que tenemos y las actualizo en la vista
        viewModel.pesetas.observe(viewLifecycleOwner) {
            binding.ptasActuales.text = it.pesetas.toString() + " ptas."
        }

        // Indicarle la cantidad del inventario
        //binding.inventarioCantidad.text = repository.dameVotos().toString().



    }

    fun setupAdapter() {
        adapter = TiendaAdapter(ArrayList(), object : TiendaAdapter.MyClickListener {
            override fun onClick(doggo: Doggo) {
                adapter.price[doggo.rarity]?.let {
                    viewModel.buyDoggo(
                        doggo.refdog.id,
                        it
                    )
                } // Por rareza, le añado el precio al doggo
            }

        })
        viewModel
        binding.rvTienda.adapter = adapter
        binding.rvTienda.layoutManager = LinearLayoutManager(requireContext())
    }
}