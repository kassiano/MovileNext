package br.com.honeyinvestimentos.aula1dataroom.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.honeyinvestimentos.aula1dataroom.model.Word
import br.com.honeyinvestimentos.aula1dataroom.model.WordRepository
import java.util.concurrent.Future

class WordViewModel : ViewModel() {

    private val repository = WordRepository()
    val allWords = repository.allWords

    //val allWords = MutableLiveData<List<Word>>()

    val insert:(Word)-> Future<Long> = repository.insert

}