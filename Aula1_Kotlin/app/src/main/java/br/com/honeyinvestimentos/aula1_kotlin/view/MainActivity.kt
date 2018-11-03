package br.com.honeyinvestimentos.aula1_kotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.honeyinvestimentos.aula1_kotlin.R
import br.com.honeyinvestimentos.aula1_kotlin.adapter.ProgrammingLanguageAdapter
import br.com.honeyinvestimentos.aula1_kotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {


    //Codeshare - compartilhar código

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = ProgrammingLanguageAdapter(
                recyclerViewItems(),
                this
        ){

            longToast("Clicked Item: ${it.title}")
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }


    fun recyclerViewItems(): List<ProgrammingLanguage>{
        val kotlin = ProgrammingLanguage(R.drawable.kotlin, "Kotlin", 2010, "Kotlin é uma linguagem")


        return listOf(kotlin, kotlin)
    }

}
