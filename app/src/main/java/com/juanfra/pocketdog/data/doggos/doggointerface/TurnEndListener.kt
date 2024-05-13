package com.juanfra.pocketdog.data.doggos.doggointerface

import com.juanfra.pocketdog.data.doggos.Doggo

interface TurnEndListener {

    var turnEndDesc : String
    fun onTurnEnd(otherdog: Doggo)
}