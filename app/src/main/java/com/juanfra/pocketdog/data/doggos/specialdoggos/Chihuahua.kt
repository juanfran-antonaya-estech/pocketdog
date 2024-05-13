package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import es.estech.myapplication.data.models.catphoto.ImagenPerroDetalle

class Chihuahua (val reference : ImagenPerroDetalle) : Doggo(reference) {

    override var baseAttackName: String
        get() = "Triple bocao"
        set(value) {}
    override var baseAttackDesc: String
        get() = "Te pega tres bocaos de ${attack} de daño"
        set(value) {}

    override fun doBaseAttack(otroperro: Doggo) {
        super.doBaseAttack(otroperro)
        super.doBaseAttack(otroperro)
        super.doBaseAttack(otroperro)
    }

}