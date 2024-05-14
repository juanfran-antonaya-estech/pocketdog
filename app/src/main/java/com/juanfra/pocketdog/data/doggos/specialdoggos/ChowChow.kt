package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class ChowChow(val reference: ImagenPerroDetalle) : Doggo(reference), BuffMove {

    var chews = 1

    override var baseAttackName: String
        get() = "Chew Chew (${chews})"
        set(value) {}
    override var baseAttackDesc: String
        get() = "Golpea ${chews} veces y aumenta el daño con cada ataque, también obtiene una parte del daño que ha hecho por golpe"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        var scalingdamage = attack.toDouble()
        for (i in 1..chews) {
            var damagedone = otroperro.getDamage(scalingdamage.toInt()).toDouble()
            scalingdamage *= 1.20
            getHealing((damagedone * 0.20).toInt())
        }
        chews++
    }

    override var buffMovName: String
        get() = "Danza bocaos"
        set(value) {}
    override var buffMovDesc: String
        get() = "duplica la cantidad de bocados (${chews * 2})"
        set(value) {}

    override fun doBuffMov(otherdoggo: Doggo) {
        chews * 2
        updatemoves()
    }

    private fun updatemoves() {
        buffMovDesc = "duplica la cantidad de bocados (${chews * 2})"
        baseAttackDesc = "Golpea ${chews} veces y aumenta el daño con cada ataque, también obtiene una parte del daño que ha hecho por golpe"
        baseAttackName = "Chew Chew (${chews})"
    }
}