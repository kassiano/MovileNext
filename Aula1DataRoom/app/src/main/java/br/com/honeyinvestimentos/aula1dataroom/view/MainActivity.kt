package br.com.honeyinvestimentos.aula1dataroom.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.honeyinvestimentos.aula1dataroom.R
import br.com.honeyinvestimentos.aula1dataroom.model.Word
import br.com.honeyinvestimentos.aula1dataroom.viewmodel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton

class MainActivity : AppCompatActivity() {

    val viewModel :WordViewModel by lazy {
        ViewModelProviders
                .of(this)
                .get(WordViewModel::class.java)
    }


    companion object {
        const val NEW_WORD_REQ_CODE = 1
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.allWords.observe(this, Observer<List<Word>> {
            words->

            words?.let {

                adapter.words = it
            }

        })



        fab.setOnClickListener {

            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_REQ_CODE)

        }


    }


    fun insertWord(word:Word){

        //Essa função se resolverá em Background
        val id = viewModel.insert(word).get()

        runOnUiThread {

            if(id!= -1L){
                alert("Inserido com sucesso","Sucesso") {
                    okButton {  }
                }.show()

            }else{
                alert("Falha ao inserir","Falha") {
                    okButton {  }
                }.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_WORD_REQ_CODE && resultCode == Activity.RESULT_OK){

            data?.let {

                val word = Word(it.getStringExtra(NewWordActivity.EXTRA_REPLY))

                insertWord(word)
            }
        }

    }


}
