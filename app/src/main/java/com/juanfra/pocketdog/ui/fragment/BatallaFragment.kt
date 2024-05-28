package com.juanfra.pocketdog.ui.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.databinding.FragmentBatallaBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.GrayscaleTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BatallaFragment : Fragment() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var binding: FragmentBatallaBinding
    val viewModel = BuscarBatallaFragment.viewModel

    private lateinit var actualenemy: Doggo

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
                hideEnemyDog()
                prepareEnemyDog(enemy)
                showEnemyDog()
            }

        }
        viewModel.nextAlly()
        Log.d("el perrito", viewModel.actualdoggo.value.toString())
        Log.d("tu trio", viewModel.yourtrio.value.toString())
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
                "ganaste" -> binding.tvLog.text = binding.tvLog.text.toString() + "\nGanaste"
                "pertiste" -> binding.tvLog.text = binding.tvLog.text.toString() + "\nPerdiste"
            }
        }


    }

    fun hideAllyDog() {
        binding.tvAllyName.animate().alpha(0f).translationX(dp(300f)).setDuration(300).start()
        binding.pbAllyLife.animate().alpha(0f).translationX(dp(300f)).setDuration(300).start()
        binding.ivAllyDog.animate().alpha(0f).translationX(dp(-300f)).setDuration(300).start()
        Thread.sleep(300L)
        binding.btNormalAtt.animate().alpha(0f).translationY(dp(35f)).setDuration(300).start()
        Thread.sleep(150L)
        binding.btSpecialAtt.animate().alpha(0f).translationY(dp(35f)).setDuration(300).start()
        Thread.sleep(150L)
        binding.btBuffAtt.animate().alpha(0f).translationY(dp(35f)).setDuration(300).start()
    }

    fun showAllyDog() {
        binding.tvAllyName.animate().alpha(1f).translationX(0f).setDuration(300).start()
        binding.pbAllyLife.animate().alpha(1f).translationX(0f).setDuration(300).start()
        binding.ivAllyDog.animate().alpha(1f).translationX(0f).setDuration(300).start()
        Thread.sleep(300L)
        binding.btNormalAtt.animate().alpha(1f).translationY(0f).setDuration(300).start()
        Thread.sleep(150L)
        binding.btSpecialAtt.animate().alpha(1f).translationY(0f).setDuration(300).start()
        Thread.sleep(150L)
        binding.btBuffAtt.animate().alpha(1f).translationY(0f).setDuration(300).start()
    }

    fun hideEnemyDog() {
        binding.tvEnemyName.animate().alpha(0f).translationY(dp(-200f)).setDuration(300).start()
        binding.pbEnemyLife.animate().alpha(0f).translationY(dp(-200f)).setDuration(300).start()
        binding.ivEnemyDog.animate().alpha(0f).translationY(dp(-200f)).setDuration(300).start()
    }

    fun showEnemyDog() {
        binding.tvEnemyName.animate().alpha(1f).translationY(0f).setDuration(300).start()
        binding.pbEnemyLife.animate().alpha(1f).translationY(0f).setDuration(300).start()
        binding.ivEnemyDog.animate().alpha(1f).translationY(0f).setDuration(300).start()
    }

    fun prepareAllyDog(ally: Doggo) {
        viewModel.actualenemy.observe(viewLifecycleOwner) { enemy ->
            binding.btNormalAtt.setOnClickListener {
                if (enemy != null) {
                    ally.doBaseAttack(enemy)
                    addLog("Has golpeado al ${enemy.refdog.breeds[0].name} con ${ally.baseAttackName}")
                    enemy.enemyturn(ally)
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
                        enemy.enemyturn(ally)
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
                        enemy.enemyturn(ally)
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
        binding.tvLog.text = binding.tvLog.text.toString() + "\n$text"
    }

}