package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import kotlin.random.Random

class Borzoi (val reference: ImagenPerroDetalle) : Doggo(reference), SpecialAttack, TurnEndListener {
    var noselength = 1
    override var specialAttName: String
        get() = "Golpe nariz (${noselength * 10}%)"
        set(value) {}
    override var specialAttDesc: String
        get() = "Ataca con su nariz, cada metro ($noselength) es un 10% de probabilidad de crítico, pero como su nariz es una baguette, cuando lo realiza, su nariz se parte a la mitad de longitud"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        val criticProb = noselength * 10
        val random = Random.nextInt(1,101)
        if (random < criticProb){
            otherdoggo.getDamage(attack * 2)
            noselength /= 2
        } else {
            otherdoggo.getDamage(attack)
        }
    }

    override var turnEndDesc: String
        get() = "Le crece la nariz 1m, lo que aumenta su probabilidad de crítico"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        noselength++

        specialAttName = "Golpe nariz (${noselength * 10}%)"
        specialAttDesc = "Ataca con su nariz, cada metro ($noselength) es un 10% de probabilidad de crítico, cuando lo realiza, su nariz vuelve a la mitad de longitud"
    }
}