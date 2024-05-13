package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import es.estech.myapplication.data.models.catphoto.ImagenPerroDetalle

class GreatDane(reference: ImagenPerroDetalle) : Doggo(reference), TurnEndListener, BuffMove {

    var bleedingStacks = 0
    var bleeding = 1

    override var rarity: String
        get() = "Legendario"
        set(value) {}
    override var baseAttackDesc: String
        get() = "Pega un bocao, añade dos acumulaciónes de sangrado"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        super.doBaseAttack(otroperro)
        bleedingStacks += 2
    }
    override var buffMovName: String
        get() = "Afilar colmillos"
        set(value) {}
    override var buffMovDesc: String
        get() = "Aumenta el daño que hace tu sangrado"
        set(value) {}

    override fun doBuffMov(otherdoggo: Doggo) {
        bleeding++
    }

    override var turnEndDesc: String
        get() = "Inflige ${(bleedingStacks * bleeding) * (attack/20).toInt()} daño de sangrado"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        if (bleedingStacks > 0){
            otherdog.getDamage((bleedingStacks * bleeding) * (attack.toDouble()/20).toInt())
            bleedingStacks--
        }
    }
}