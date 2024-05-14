package com.juanfra.pocketdog.data.doggos

import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.models.breeds.Height
import com.juanfra.pocketdog.data.models.breeds.Weight
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import kotlin.random.Random

open class Doggo(public val refdog: ImagenPerroDetalle) {
    //las stats iniciales del perro
    val basehealth: Int = parseHealth(refdog.breeds[0].height, refdog.breeds[0].weight)
    val baseattack: Int = parseAttack(refdog.breeds[0].height)
    val basedefense: Int = parseDefense(refdog.breeds[0].weight)
    var alive : Boolean = true
    open var rarity = "Común"

    //estas stats son las que luego se utilizan en el combate
    var maxhealth = basehealth
    var actualhealth = maxhealth
    var attack = baseattack
    var defense = basedefense

    //cosas del ataque base
    open var baseAttackName = "Bocao"
    open var baseAttackDesc = "Te pega un bocao y te hace ${attack} de daño"
    open fun doBaseAttack(otroperro: Doggo){
        otroperro.getDamage(attack)
    }

    private fun enemyturn(playerDoggo: Doggo) {
        if (this is SpecialAttack && this is BuffMove){
            val rand = Random.nextDouble(1.0)
            if (rand > 0.5){
                (this as SpecialAttack).doSpecialAtt(playerDoggo)
            } else {
                (this as BuffMove).doBuffMov(playerDoggo)
            }
        } else if (this is SpecialAttack){
            (this as SpecialAttack).doSpecialAtt(playerDoggo)
        } else if (this is BuffMove) {
            val rand = Random.nextDouble(1.0)
            if (rand > 0.5){
                this.doBaseAttack(playerDoggo)
            } else {
                (this as BuffMove).doBuffMov(playerDoggo)
            }
        } else {
            this.doBaseAttack(playerDoggo)
        }
    }

    //una función para convertir el daño bruto de un ataque en daño total aplicandole la reducción de defensa
    //https://leagueoflegends.fandom.com/wiki/Armor
    open fun getDamage(damage: Int) : Int  {
        val defenseDouble : Double = defense.toDouble()
        val damagereduction = damage / (1 + defenseDouble / 100)
        val neatdamage : Int = damagereduction.toInt()
        actualhealth -= neatdamage

        if (actualhealth <= 0){
            death()
        }

        return neatdamage
    }
    //Esta funcion es para no curarse por encima de lo permitido y curarse siempre un valor absoluto
    open fun getHealing(heal: Int) : Int {
        if (actualhealth >= maxhealth){
            return 0
        } else if (actualhealth < maxhealth && actualhealth + heal > maxhealth){
            val diferencia = maxhealth - actualhealth
            actualhealth = maxhealth
            return diferencia

        } else {
            actualhealth += heal
            return heal
        }
    }

    //esto es por si se muere, algunos perros podrían usarla
    fun death() {
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