package br.com.honeyinvestimentos.aula1_kotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback

class RepositoryRetriver {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }


    private val service: GitHubService

    init{

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(GitHubService::class.java)
    }

    fun getLanguageRepositories(callback: Callback<GithubRepositoriesResult>,
                                query:String){


        val call = service.searchRepositories("languages:$query")

        call.enqueue(callback)
    }
}