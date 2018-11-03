package br.com.honeyinvestimentos.aula1dataroom.model

import android.arch.lifecycle.LiveData
import org.jetbrains.anko.doAsyncResult
import java.util.concurrent.Future

class WordRepository{

    private val wordDao: WordDao
    val allWords: LiveData<List<Word>>

    init {
        wordDao = room.wordDao()
        allWords = wordDao.getAllWord()
    }

    val insert: (Word)-> Future<Long> = {
         doAsyncResult {
            wordDao.insert(it)
        }
    }
}