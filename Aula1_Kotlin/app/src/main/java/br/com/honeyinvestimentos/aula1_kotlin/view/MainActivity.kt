package br.com.honeyinvestimentos.aula1_kotlin.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.honeyinvestimentos.aula1_kotlin.R
import br.com.honeyinvestimentos.aula1_kotlin.adapter.ProgrammingLanguageAdapter
import br.com.honeyinvestimentos.aula1_kotlin.adapter.RepositoryAdapter
import br.com.honeyinvestimentos.aula1_kotlin.api.GithubRepositoriesResult
import br.com.honeyinvestimentos.aula1_kotlin.api.RepositoryRetriver
import br.com.honeyinvestimentos.aula1_kotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    //Codeshare - compartilhar código

    private val repositoryRetriver = RepositoryRetriver()

    private val callback = object :Callback<GithubRepositoriesResult>{
        override fun onFailure(call: Call<GithubRepositoriesResult>, t: Throwable) {
            longToast("Fail loading reps")
        }

        override fun onResponse(call: Call<GithubRepositoriesResult>, response: Response<GithubRepositoriesResult>) {
            longToast("Load suceed")


            if(response.isSuccessful){

                val resultList = response.body()?.repositories?: emptyList()

                recyclerView.adapter = RepositoryAdapter(resultList, this@MainActivity){
                    longToast("Item clicado: ${it.full_name}")
                }

            }

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDefaultRecyclerView()
    }

    private fun loadDefaultRecyclerView() {
        recyclerView.adapter = ProgrammingLanguageAdapter(
                recyclerViewItems(),
                this
        ) {

            repositoryRetriver.getLanguageRepositories(callback, it.title)
        }

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }


    fun recyclerViewItems(): List<ProgrammingLanguage>{
        val kotlin = ProgrammingLanguage(R.drawable.kotlin, "Kotlin", 2010, "Kotlin é uma linguagem")


        return listOf(kotlin, kotlin)
    }

}
