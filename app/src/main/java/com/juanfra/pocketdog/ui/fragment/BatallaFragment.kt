package com.juanfra.pocketdog.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.juanfra.pocketdog.R
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.databinding.FragmentBatallaBinding
import com.juanfra.pocketdog.ui.MainActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BatallaFragment : Fragment() {
    private var mediaPlayer : MediaPlayer? = null
    private lateinit var binding: FragmentBatallaBinding
    val viewModel = BuscarBatallaFragment.viewModel
    private lateinit var actualenemy: Doggo
    private lateinit var actualdoggo: Doggo

    override fun onStart() {
        super.onStart()
        mediaPlayer = MediaPlayer.create(context, R.raw.pdbatalla)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }
    override fun onStop() {
        super.onStop()
        if (mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBatallaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var lastdogId = ""
        var lastenemyId = ""

        viewModel.nextEnemy()

        viewModel.actualenemy.observe(viewLifecycleOwner) { enemy ->
            if (enemy.refdog.id != lastenemyId) {
                lastenemyId = enemy.refdog.id
                prepareEnemyDog(enemy)
            }

        }
        viewModel.nextAlly()
        viewModel.actualdoggo.observe(viewLifecycleOwner) { ally ->
            if (ally.refdog.id != lastdogId) {
                lastdogId = ally.refdog.id
                prepareAllyDog(ally)
            } else {
                prepareAllyDog(ally)
            }
        }

        viewModel.win.observe(viewLifecycleOwner) {
            when (it) {
                "ganaste" -> {
                    Toast.makeText(requireContext(), "Ganaste", Toast.LENGTH_SHORT).show()
                    viewModel.whenWin()
                    resultado(true)
                    findNavController().navigateUp()
                }
                "perdiste" -> {
                    Toast.makeText(requireContext(), "Perdiste", Toast.LENGTH_SHORT).show()
                    resultado(false)
                    findNavController().navigateUp()
                }
            }
        }


    }

    override fun onResume() {
        super.onResume()
        // Cambiar el título de la Toolbar
        (activity as? MainActivity)?.setToolbarTitle("Combate")
    }

    fun prepareAllyDog(ally: Doggo) {
        viewModel.actualenemy.observe(viewLifecycleOwner) { enemy ->
            actualenemy = enemy
            actualdoggo = ally
            val log = object : Doggo.Log{
                override fun action(text: String) {
                    addLog(text)
                }

            }
            binding.btNormalAtt.setOnClickListener {
                if (enemy != null) {
                    ally.doBaseAttack(enemy)
                    addLog("Has golpeado al ${enemy.refdog.breeds[0].name} con ${ally.baseAttackName}")
                    enemy.enemyturn(ally, log)
                    if (ally is TurnEndListener) {
                        ally.onTurnEnd(enemy)
                    }
                    if (enemy is TurnEndListener) {
                        enemy.onTurnEnd(ally)
                    }
                    prepareEnemyDog(enemy)
                    prepareAllyDog(ally)
                    if (!ally.alive) {
                        viewModel.doggoDeath(ally)
                        viewModel.nextAlly()
                    }
                    if (!enemy.alive) {
                        viewModel.enemyDeath(enemy)
                        viewModel.nextEnemy()

                    }
                }
            }

            if (ally is SpecialAttack) {
                binding.btSpecialAtt.visibility = View.VISIBLE
                binding.btSpecialAtt.text = ally.specialAttName
                binding.btSpecialAtt.setOnClickListener {
                    if (enemy != null) {
                        addLog("Has usado ${ally.specialAttName}: ${ally.specialAttDesc}")
                        ally.doSpecialAtt(enemy)
                        enemy.enemyturn(ally, log)
                        if (ally is TurnEndListener) {
                            ally.onTurnEnd(enemy)
                        }
                        if (enemy is TurnEndListener) {
                            enemy.onTurnEnd(ally)
                        }
                        prepareEnemyDog(enemy)
                        prepareAllyDog(ally)
                        if (!ally.alive) {
                            viewModel.doggoDeath(ally)
                            viewModel.nextAlly()
                        }
                        if (!enemy.alive) {
                            viewModel.enemyDeath(enemy)
                            viewModel.nextEnemy()

                        }
                    }
                }
            } else {
                binding.btSpecialAtt.visibility = View.GONE
                binding.btSpecialAtt.setOnClickListener {}
            }
            if (ally is BuffMove) {
                binding.btBuffAtt.visibility = View.VISIBLE
                binding.btBuffAtt.text = ally.buffMovName
                binding.btBuffAtt.setOnClickListener {
                    if (enemy != null) {
                        addLog("Has usado ${ally.buffMovName}: ${ally.buffMovDesc}")
                        ally.doBuffMov(enemy)
                        enemy.enemyturn(ally, log)
                        if (ally is TurnEndListener) {
                            ally.onTurnEnd(enemy)
                        }
                        if (enemy is TurnEndListener) {
                            enemy.onTurnEnd(ally)
                        }
                        prepareEnemyDog(enemy)
                        prepareAllyDog(ally)
                        if (!ally.alive) {
                            viewModel.doggoDeath(ally)
                            viewModel.nextAlly()
                        }
                        if (!enemy.alive) {
                            viewModel.enemyDeath(enemy)
                            viewModel.nextEnemy()

                        }
                    }
                }
            } else {
                binding.btBuffAtt.visibility = View.GONE
                binding.btBuffAtt.setOnClickListener {}
            }
        }

        if (ally.alive) {
            Picasso.get()
                .load(ally.refdog.url)
                .into(binding.ivAllyDog)
        } else {
            Picasso.get()
                .load(ally.refdog.url)
                .transform(GrayscaleTransformation())
                .into(binding.ivAllyDog)
        }
        binding.tvAllyName.text = ally.refdog.breeds[0].name
        binding.tvAllyLife.text = "${ally.actualhealth}/${ally.maxhealth}"
        binding.pbAllyLife.max = ally.maxhealth
        binding.pbAllyLife.progress = ally.actualhealth
        binding.tvAtt.text = "Ataque: ${ally.attack}"
        binding.tvDef.text = "Defensa: ${ally.defense}"

        binding.btNormalAtt.text = ally.baseAttackName
    }

    fun prepareEnemyDog(enemy: Doggo) {
        if (enemy.alive) {
            Picasso.get()
                .load(enemy.refdog.url)
                .into(binding.ivEnemyDog)
        } else {
            Picasso.get()
                .load(enemy.refdog.url)
                .transform(GrayscaleTransformation())
                .into(binding.ivEnemyDog)
        }
        binding.tvEnemyName.text = enemy.refdog.breeds[0].name
        binding.tvEnemyLife.text = "${enemy.actualhealth}/${enemy.maxhealth}"
        binding.pbEnemyLife.max = enemy.maxhealth
        binding.pbEnemyLife.progress = enemy.actualhealth
    }

    fun dp(dipValue: Float): Float {
        val metrics = requireContext().resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
    }

    fun addLog(text: String) {
        val texto = binding.tvLog.text.toString()
        var lineas = ArrayList<String>()
        if (texto.isNotEmpty()) {
            lineas.addAll(texto.split("\n"))
        }
        while (lineas.size > 15){
            lineas.removeAt(0)
        }
        binding.tvLog.text = lineas.joinToString("\n") + "\n$text"
    }
     fun resultado(resultado: Boolean){
        val resultadobatalla = Resultado(actualdoggo.refdog.url.toString(),actualenemy.refdog.url.toString(),resultado)
        viewModel.logBatalla(resultadobatalla)
    }

}