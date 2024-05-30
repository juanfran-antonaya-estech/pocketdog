package com.juanfra.pocketdog.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.FragmentInicioBinding
import com.juanfra.pocketdog.ui.MainActivity
import com.juanfra.pocketdog.data.pesetas.Pesetas
import com.juanfra.pocketdog.databinding.FragmentTiendaBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation


class InicioFragment : Fragment() {
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    val viewModels by activityViewModels<PesetasViewModel> {
        PesetasViewModel.PesetasViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaPlayer = MediaPlayer.create(context, R.raw.pdtienda3)
        mediaPlayer.start()
        viewModels.loadDoggos()
       peseteo()
        viewModels.showcaseenemies().observe(viewLifecycleOwner) {
            if (it != null) {
                fillBattleinclude(it)
            }
        }
        viewModels.showcaseenemies().observe(viewLifecycleOwner) {
            if (it != null) {
                fillStoreinclude(it)
            }
        }
        viewModels.yourtrio.observe(viewLifecycleOwner){
            if (it != null) {
                fillInvInclude(it)
            }
        }
    }

    fun fillInvInclude(dogtrio: DogTrio){
        val ibinding = binding.inInventario
        ibinding.btAccionPlantilla.visibility = Button.GONE
        ibinding.tvPacknamePlantilla.text = "Tus perros (${dogtrio.perros.size}/3)"
        ibinding.tvPackLevelPlantilla.visibility = TextView.GONE

        if (dogtrio.perros.size > 0) {
            when (dogtrio.perros.size) {
                1 -> {
                    Picasso.get()
                        .load(dogtrio.perros[0].refdog.url)
                        .into(ibinding.ivPerro1Plantilla)
                }
                2 -> {
                    Picasso.get()
                        .load(dogtrio.perros[0].refdog.url)
                        .into(ibinding.ivPerro1Plantilla)
                    Picasso.get()
                        .load(dogtrio.perros[1].refdog.url)
                        .into(ibinding.ivPerro2Plantilla)
                }
                3 -> {
                    Picasso.get()
                        .load(dogtrio.perros[0].refdog.url)
                        .into(ibinding.ivPerro1Plantilla)
                    Picasso.get()
                        .load(dogtrio.perros[1].refdog.url)
                        .into(ibinding.ivPerro2Plantilla)
                    Picasso.get()
                        .load(dogtrio.perros[2].refdog.url)
                        .into(ibinding.ivPerro3Plantilla)
                }
            }
        }
    }

    fun fillStoreinclude(dogtrio: DogTrio){
        val sbinding = binding.inTienda
        sbinding.btAccionPlantilla.visibility = Button.GONE
        sbinding.tvPacknamePlantilla.text = "Tienda"
        sbinding.tvPackLevelPlantilla.visibility = TextView.GONE
        binding.barraTienda.animate().setDuration(1000).translationX(0F).start()
        dogtrio.perros[0]?.let {
            Picasso.get()
                .load(it.refdog.url)
                .into(sbinding.ivPerro1Plantilla)
        }
        dogtrio.perros[1]?.let {
            Picasso.get()
                .load(it.refdog.url)
                .into(sbinding.ivPerro2Plantilla)
        }
        dogtrio.perros[2]?.let {
            Picasso.get()
                .load(it.refdog.url)
                .into(sbinding.ivPerro3Plantilla)
        }
    }
    fun fillBattleinclude(dogtrio: DogTrio) {
        val bbinding = binding.inBatalla
        bbinding.btAccionPlantilla.text = "Batallar"
        binding.barraBatalla.animate().setDuration(1000).translationX(0F).start()
        Picasso.get()
            .load(dogtrio.perros[0].refdog.url)
            .into(bbinding.ivPerro1Plantilla)
        Picasso.get()
            .load(dogtrio.perros[1].refdog.url)
            .into(bbinding.ivPerro2Plantilla)
        Picasso.get()
            .load(dogtrio.perros[2].refdog.url)
            .into(bbinding.ivPerro3Plantilla)
        bbinding.tvPackLevelPlantilla.text = dogtrio.packLevel
        bbinding.tvPacknamePlantilla.text = dogtrio.packName

        bbinding.btAccionPlantilla.setOnClickListener {
            viewModels.battleTrio(dogtrio)
            viewModels.resetBattle()
            BuscarBatallaFragment.viewModel = viewModels
            findNavController().navigate(R.id.action_inicioFragment_to_batallaFragment)
        }

    }

    override fun onResume() {
        super.onResume()
        // Cambiar el título de la Toolbar
        (activity as? MainActivity)?.setToolbarTitle("Inicio")
    }

        private fun peseteo(){
            viewModels.misPesetas.observe(viewLifecycleOwner){
                if (it.size == 0) {
                    viewModels.insertarPesetas(Pesetas(1500))
                } else {
                    val pesetas = it[0]
                    binding.marcadorPuntos.text = "Pesetas: ${pesetas.pesetas}"
                }
            }

    }
}