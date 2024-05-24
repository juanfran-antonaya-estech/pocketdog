package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle
import kotlin.random.Random

class SharPei(val reference: ImagenPerroDetalle) : Doggo(reference), SpecialAttack {

    override var rarity: String
        get() = "Raro"
        set(value) {}

    var evasion = 10.0
    override var specialAttName: String
        get() = "Pellejoso"
        set(value) {}
    override var specialAttDesc: String
        get() = "Ataca y aumenta tu evasión, hasta subir completamente"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        otherdoggo.getDamage(attack)
        evasion *= 1.5
    }

    override fun getDamage(damage: Int): Int {
        val random = Random.nextDouble(1.0)
        if (random >= (evasion / 100)){
            return super.getDamage(damage)
        } else {
            return 0
        }
    }


}