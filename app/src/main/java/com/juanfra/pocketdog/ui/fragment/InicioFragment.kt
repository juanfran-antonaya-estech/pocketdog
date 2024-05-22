package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.DogTrio
import com.juanfra.pocketdog.databinding.FragmentInicioBinding
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.gpu.PixelationFilterTransformation
import jp.wasabeef.picasso.transformations.gpu.ToonFilterTransformation


class InicioFragment : Fragment() {

    val viewModels by activityViewModels<PesetasViewModel> {
        PesetasViewModel.PesetasViewModelFactory(requireContext())
    }
    private lateinit var binding: FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModels.showcaseenemies().observe(viewLifecycleOwner) {
            if (it != null) {
                fillBattleinclude(it)
            }
        }
    }

    fun fillBattleinclude(dogtrio: DogTrio) {
        val bbinding = binding.inBatalla
        bbinding.btAccionPlantilla.text = "Batallar"
        val threshold = 20F
        binding.barraBatalla.animate().setDuration(1000).translationX(0F).start()
        Picasso.get()
            .load(dogtrio.perros[0].refdog.url)
            .transform(PixelationFilterTransformation(requireContext(), threshold))
            .into(bbinding.ivPerro1Plantilla)
        Picasso.get()
            .load(dogtrio.perros[1].refdog.url)
            .transform(PixelationFilterTransformation(requireContext(), threshold))
            .into(bbinding.ivPerro2Plantilla)
        Picasso.get()
            .load(dogtrio.perros[2].refdog.url)
            .transform(PixelationFilterTransformation(requireContext(), threshold))
            .into(bbinding.ivPerro3Plantilla)
        bbinding.tvPackLevelPlantilla.text = dogtrio.packLevel
        bbinding.tvPacknamePlantilla.text = dogtrio.packName

        bbinding.btAccionPlantilla.setOnClickListener {
            viewModels.battleTrio(dogtrio)
            BuscarBatallaFragment.viewModel = viewModels
            findNavController().navigate(R.id.action_inicioFragment_to_batallaFragment)
        }

    }

}