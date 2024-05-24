package com.juanfra.pocketdog.data.doggos.doggointerface

import com.juanfra.pocketdog.data.doggos.Doggo

interface BuffMove {
    var buffMovName : String
    var buffMovDesc : String
    fun doBuffMov(otherdoggo: Doggo)
}