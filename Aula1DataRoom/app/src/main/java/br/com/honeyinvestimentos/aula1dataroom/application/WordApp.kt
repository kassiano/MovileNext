package br.com.honeyinvestimentos.aula1dataroom.application

import android.app.Application
import br.com.honeyinvestimentos.aula1dataroom.model.initRoom

class WordApp:Application() {

    override fun onCreate() {
        super.onCreate()
        initRoom(this)
    }
}