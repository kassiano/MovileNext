package br.com.honeyinvestimentos.day3_projetohandson.base

import android.arch.lifecycle.ViewModel
import br.com.honeyinvestimentos.day3_projetohandson.di.component.DaggerViewModelInjector
import br.com.honeyinvestimentos.day3_projetohandson.di.component.ViewModelInjector
import br.com.honeyinvestimentos.day3_projetohandson.di.module.NetworkModule
import br.com.honeyinvestimentos.day3_projetohandson.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {

    private val injector: ViewModelInjector =
        DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule) .build()
    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}