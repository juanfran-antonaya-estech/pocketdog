package com.juanfra.pocketdog.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.databinding.FragmentDescripcionPerrosBinding
import com.juanfra.pocketdog.databinding.FragmentMisPerrosBinding
import com.juanfra.pocketdog.ui.adapter.InventarioAdapter
import com.juanfra.pocketdog.ui.viewmodel.PesetasViewModel
import com.squareup.picasso.Picasso

class DescripcionPerrosFragment : Fragment() {
    private lateinit var binding : FragmentDescripcionPerrosBinding
    //private lateinit var adapter:
    private val viewModel by activityViewModels<PesetasViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescripcionPerrosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.actualdoggo.observe(viewLifecycleOwner) {
            binding.nombre.text = it.refdog.breeds[0].name

            if (it is BuffMove){
                binding.descripcion.text = it.buffMovDesc
            }

            if (it is SpecialAttack){
                binding.descripcion.text = it.specialAttDesc
            }

            if (it is TurnEndListener){
                binding.descripcion.text = it.turnEndDesc
            }

            binding.descripcion.text = it.refdog.breeds[0].description + "\n" + it.baseAttackDesc
            binding.estadisticas.text = " Vida: " + it.actualhealth.toString() + "/" + it.maxhealth + "\n Ataque: " + it.baseattack + "\n Defensa: " + it.basedefense

            Picasso.get()
                .load(it.refdog.url)
                .into(binding.imagenPerros)
        }
    }

}