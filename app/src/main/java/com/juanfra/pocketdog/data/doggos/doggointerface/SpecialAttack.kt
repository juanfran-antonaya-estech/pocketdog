package com.juanfra.pocketdog.data.doggos.doggointerface

import com.juanfra.pocketdog.data.doggos.Doggo

interface SpecialAttack {
    var specialAttName : String
    var specialAttDesc : String
    fun doSpecialAtt(otherdoggo : Doggo)
}