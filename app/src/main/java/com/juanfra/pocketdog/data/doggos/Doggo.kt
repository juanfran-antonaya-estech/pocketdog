package com.juanfra.pocketdog.data.doggos

import com.juanfra.pocketdog.data.models.breeds.Height
import com.juanfra.pocketdog.data.models.breeds.Weight
import es.estech.myapplication.data.models.catphoto.ImagenPerroDetalle
import kotlinx.coroutines.newFixedThreadPoolContext

class Doggo(public val refdog: ImagenPerroDetalle) {
    //las stats iniciales del perro
    val basehealth: Int = parseHealth(refdog.breeds[0].height, refdog.breeds[0].weight)
    val baseattack: Int = parseAttack(refdog.breeds[0].height)
    val basedefense: Int = parseDefense(refdog.breeds[0].weight)
    var alive : Boolean = true

    //estas stats son las que luego se utilizan en el combate
    var maxhealth = basehealth
    var actualhealth = maxhealth
    var attack = baseattack
    var defense = basedefense

    //cosas del ataque base
    var baseAttackName = "Bocao"
    var baseAttackDesc = "Te pega un bocao y te hace ${attack} de daño"
    fun doBaseAttack(otroperro: Doggo){
        otroperro.getDamage(attack)
    }

    //una función para convertir el daño bruto de un ataque en daño total aplicandole la reducción de defensa
    //https://leagueoflegends.fandom.com/wiki/Armor
    private fun getDamage(damage: Int) : Int  {
        val defenseDouble : Double = defense.toDouble()
        val damagereduction = damage / (1 + defenseDouble / 100)
        val neatdamage : Int = damagereduction.toInt()
        actualhealth -= neatdamage

        if (actualhealth <= 0){
            death()
        }

        return neatdamage
    }

    private fun death() {
        alive = false
    }

    //funciones para obtener las stats de ImagenPerroDetalle
    private fun parseDefense(weight: Weight): Int {
        val weightsb = weight.metric.substringAfter("- ")
        val weight: Double = weightsb.toDouble()

        return weight.toInt()
    }

    private fun parseAttack(height: Height): Int {
        val heightsb = height.metric.substringAfter("- ")
        val height: Double = heightsb.toDouble()
        return height.toInt()
    }

    private fun parseHealth(height: Height, weight: Weight): Int {
        val heightsb = height.metric.substringAfter("- ")
        val height: Double = heightsb.toDouble()

        val weightsb = weight.metric.substringAfter("- ")
        val weight: Double = weightsb.toDouble()

        return (height * weight).toInt()
    }
}