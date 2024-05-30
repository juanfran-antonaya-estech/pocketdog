package com.juanfra.pocketdog.data.doggos.specialdoggos

import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class Pug(val reference : ImagenPerroDetalle) : Doggo(reference),TurnEndListener,SpecialAttack {

    override var rarity: String
        get() = "Épico"
        set(value) {}

    override var turnEndDesc: String
        get() = "Pierde 1 de vida cada turno por problemas respiratorios, cuando muere, hace su vida máxima como daño"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        this.actualhealth--
        if (actualhealth <= 0){
            otherdog.getDamage(this.maxhealth)
            this.death()
        }
    }

    override var specialAttName: String
        get() = "Notorius P.U.G."
        set(value) {}
    override var specialAttDesc: String
        get() = "Aumenta su vida máxima en 20"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        this.maxhealth += 20
    }
}