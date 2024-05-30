package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class Husky(val reference: ImagenPerroDetalle) : Doggo(reference),SpecialAttack,BuffMove {
    override var rarity: String
        get() = "Épico"
        set(value) {}

    override var buffMovName: String
        get() = "Robar calor"
        set(value) {}
    override var buffMovDesc: String
        get() = "Obtiene la diferencia de armadura base y armadura actual del otro perro como vida"
        set(value) {}

    override fun doBuffMov(otherdoggo: Doggo) {
        this.actualhealth += Math.abs(otherdoggo.defense - otherdoggo.basedefense)
    }

    override var specialAttName: String
        get() = "Bocao Helado"
        set(value) {}
    override var specialAttDesc: String
        get() = "Hace la mitad de daño pero reduce la defensa un 20%"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        otherdoggo.getDamage(attack / 2)
        otherdoggo.defense -= (otherdoggo.defense.toDouble() * 0.20).toInt()
    }

}