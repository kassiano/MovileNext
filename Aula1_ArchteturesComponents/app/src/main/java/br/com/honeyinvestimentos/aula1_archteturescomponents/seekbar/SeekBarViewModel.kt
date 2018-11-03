package br.com.honeyinvestimentos.aula1_archteturescomponents.seekbar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SeekBarViewModel : ViewModel() {

    val seekBarValue = MutableLiveData<Int>()



}