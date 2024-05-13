package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import es.estech.myapplication.data.models.catphoto.ImagenPerroDetalle

class Pug(reference : ImagenPerroDetalle) : Doggo(reference),TurnEndListener {
    override var turnEndDesc: String
        get() = "Pierde 1 de vida cada turno por problemas respiratorios"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        this.actualhealth--
        if (actualhealth <= 0){
            this.death()
        }
    }
}