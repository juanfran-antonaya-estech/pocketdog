package com.juanfra.pocketdog.data.doggos.sdvol2

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import kotlin.random.Random

class BullTerrier (reference: ImagenPerroDetalle) : Doggo(reference), BuffMove {

    var cabezazo = 1

    override var baseAttackName: String
        get() = "Cabezazo (${cabezazo})"
        set(value) {}

    override var baseAttackDesc: String
        get() = "Cada tres cabezazos golpeas con la mitad de tu defensa"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        if (cabezazo % 3 == 0){
            cabezazo = 1
            otroperro.getDamage(attack + (defense / 2))
        } else {
            super.doBaseAttack(otroperro)
            cabezazo++
        }
        baseAttackName = "Cabezazo (${cabezazo})"
    }
    override var rarity: String
        get() = "Épico"
        set(value) {}
    override var buffMovName: String
        get() = "Endurecer"
        set(value) {}
    override var buffMovDesc: String
        get() = "Aumenta tu defensa, hay un 20% de probabilidad de que se duplique"
        set(value) {}

    override fun doBuffMov(otherdoggo: Doggo) {
        if (Random.nextFloat() < 0.2){
            defense *= 2
        } else {
            defense += 10
        }
    }

}