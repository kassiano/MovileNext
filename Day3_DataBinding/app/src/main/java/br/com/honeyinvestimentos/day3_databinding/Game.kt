package br.com.honeyinvestimentos.day3_databinding

import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.honeyinvestimentos.day3_databinding.utils.bindable

class Game(name:String,
           val lauchYear: Int,
           val imageUrl:String,
           rating:Double) : BaseObservable() {

    val isClassic = lauchYear < 2000

    @get:Bindable
    var rating by bindable(rating, BR.rating)

    @get:Bindable
    var name by bindable(name, BR.name)

}
