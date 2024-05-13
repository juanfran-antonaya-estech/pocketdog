package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import es.estech.myapplication.data.models.catphoto.ImagenPerroDetalle

class PerroSanchez(val reference : ImagenPerroDetalle) : Doggo(reference) {
    override var rarity: String
        get() = "Boss"
        set(value) {}
    override var baseAttackName: String
        get() = "Impuestos"
        set(value) {}
    override var baseAttackDesc: String
        get() = "Te roba la mitad de la vida y la defensa"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        this.actualhealth += otroperro.actualhealth/2
        otroperro.actualhealth /= 2
        this.defense += otroperro.defense/2
        otroperro.defense /= 2
    }
}