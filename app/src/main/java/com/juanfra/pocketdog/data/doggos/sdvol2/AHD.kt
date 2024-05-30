package com.juanfra.pocketdog.data.doggos.sdvol2

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class AHD(var reference: ImagenPerroDetalle) : Doggo(reference), BuffMove, TurnEndListener {

    var poisonstacks = 0
    override var rarity: String
        get() = "Épico"
        set(value) {}
    override var buffMovName: String
        get() = "Sacar dardos"
        set(value) {}
    override var buffMovDesc: String
        get() = "Quita todos los dardos de golpe, cada dardo hace un 2% de daño por cada dardo clavado"
        set(value) {}

    override fun doBuffMov(otherdoggo: Doggo) {
        if (poisonstacks > 0) {
            otherdoggo.getDamage((otherdoggo.maxhealth.toDouble() * 0.02).toInt() * poisonstacks)
            poisonstacks = 0
        }
    }

    override var baseAttackName: String
        get() = "Tirar dardo (${poisonstacks})"
        set(value) {}

    override var baseAttackDesc: String
        get() = "Tira un dardo al enemigo, solo hace la mitad de daño"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        otroperro.getDamage(attack / 2)
        poisonstacks++
        baseAttackName = "Tirar dardo (${poisonstacks})"
    }
    override var turnEndDesc: String
        get() = "hace un 1% de daño según la vida máxima del otro perro si tienes dardos clavados"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        if (poisonstacks > 0) {
            otherdog.getDamage((otherdog.maxhealth.toDouble() * 0.01).toInt())
        }
    }
}