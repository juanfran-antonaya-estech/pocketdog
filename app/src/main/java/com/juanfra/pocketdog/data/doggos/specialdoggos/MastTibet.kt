package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class MastTibet(reference: ImagenPerroDetalle) : Doggo(reference),SpecialAttack {
    override var specialAttName: String
        get() = "Golpe de escudo"
        set(value) {}
    override var specialAttDesc: String
        get() = "Golpea con la defensa en lugar del ataque"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        otherdoggo.getDamage(defense)
    }

    override var rarity: String
        get() = "Legendary"
        set(value) {}

    override fun getDamage(damage: Int): Int {
        val damage = super.getDamage(damage)
        defense += damage
        return damage
    }
}