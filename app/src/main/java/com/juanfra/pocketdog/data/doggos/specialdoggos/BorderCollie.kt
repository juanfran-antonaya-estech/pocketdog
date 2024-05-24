package com.juanfra.pocketdog.data.doggos.specialdoggos


import com.juanfra.pocketdog.data.doggos.Doggo
import com.juanfra.pocketdog.data.doggos.doggointerface.BuffMove
import com.juanfra.pocketdog.data.doggos.doggointerface.SpecialAttack
import com.juanfra.pocketdog.data.doggos.doggointerface.TurnEndListener
import com.juanfra.pocketdog.data.models.catphoto.ImagenPerroDetalle

class BorderCollie (reference: ImagenPerroDetalle) : Doggo(reference), TurnEndListener, SpecialAttack, BuffMove{

    var sheeps = 0
    val sheepatt = 120
    val sheepdef = 45

    override var rarity: String
        get() = "Raro"
        set(value) {}

    override var specialAttName: String
        get() = "Estampida de rebaño (${(sheeps / 2) * sheepatt})"
        set(value) {}
    override var specialAttDesc: String
        get() = "Golpea con la mitad del rebaño"
        set(value) {}

    override fun doSpecialAtt(otherdoggo: Doggo) {
        otherdoggo.getDamage((sheeps / 2) * sheepatt)
    }

    override var turnEndDesc: String
        get() = "Añade una oveja al rebaño, cada oveja le proporciona al Border Collie ${sheepdef} de defensa"
        set(value) {}

    override fun onTurnEnd(otherdog: Doggo) {
        sheeps++
        updatemoves()
    }

    private fun updatemoves() {
        specialAttName = "Estampida de rebaño (${(sheeps / 2) * sheepatt})"
        defense = basedefense + (sheeps * sheepdef)
    }

    override var buffMovName: String
        get() = "Agrupar rebaño"
        set(value) {}
    override var buffMovDesc: String
        get() = "Agrupa las ovejas, cada oveja le da 5pts de salud"
        set(value) {}

    override fun doBuffMov(otherdoggo: Doggo) {
        getHealing(sheeps * 5)
    }


}