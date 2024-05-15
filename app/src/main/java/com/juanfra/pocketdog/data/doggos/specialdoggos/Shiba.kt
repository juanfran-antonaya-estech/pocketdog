package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import kotlin.random.Random

class Shiba (val reference: ImagenPerroDetalle): Doggo(reference), SpecialAttack {

    var money = 0
    override var specialAttName: String
        get() = "Cañón de monedas (${money})"
        set(value) {}
    override var specialAttDesc: String
        get() = "Dispara todas las cargas de monedas (${money}), cada moneda hace 2 de daño (${money * 2})"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        otherdoggo.getDamage(money * 2)
        money = 0
        actualizarAtaques()
    }

    override var rarity: String
        get() = "Legendario"
        set(value) {}
    override var baseAttackName: String
        get() = "Bocao dorado"
        set(value) {}
    override var baseAttackDesc: String
        get() = "Haz daño y obtiene entre 1 y 5 monedas"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        super.doBaseAttack(otroperro)
        money += Random.nextInt(1, 6)
        actualizarAtaques()
    }

    private fun actualizarAtaques() {
        specialAttName = "Cañón de monedas (${money})"
        specialAttDesc = "Dispara todas las cargas de monedas (${money}), cada moneda hace 2 de daño (${money * 2})"
    }
}