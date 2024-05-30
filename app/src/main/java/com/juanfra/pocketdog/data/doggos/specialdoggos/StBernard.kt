package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class StBernard(reference : ImagenPerroDetalle) : Doggo(reference), TurnEndListener {
    override var rarity: String
        get() = "Épico"
        set(value) {}
    override var turnEndDesc: String
        get() = "se cura un 5% de vida máxima"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        val healthcalc = maxhealth * 0.05
        getHealing(healthcalc.toInt())
    }

    override fun getDamage(damage: Int): Int {
        val calculatedDamage = super.getDamage(damage)
        maxhealth += calculatedDamage / 5
        return calculatedDamage
    }

}