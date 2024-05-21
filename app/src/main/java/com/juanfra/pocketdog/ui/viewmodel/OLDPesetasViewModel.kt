package com.juanfra.pocketdog.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class OLDPesetasViewModel() : ViewModel() {
    var liveData: MutableLiveData<Int> = MutableLiveData()

    fun setPesetas() {
        liveData.value = 1200
    }

    fun getPesetas() : Int? {
        return liveData.value
    }

    fun plusPesetas(sum: Int) {
        liveData.value?.plus(sum)
    }

    fun minusPesetas(min: Int) {
        liveData.value = liveData.value?.minus(min)
    }

    fun savePesetas(pesetas: Int?) {
        liveData = pesetas as MutableLiveData<Int>
    }

}