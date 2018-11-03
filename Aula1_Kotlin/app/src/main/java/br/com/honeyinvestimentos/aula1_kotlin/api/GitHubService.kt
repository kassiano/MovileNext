package br.com.honeyinvestimentos.aula1_kotlin.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {


    @GET("/search/repositories")
    fun searchRepositories(@Query("q") query:String,
                           @Query("sorte") sort:String = "starts",
                            @Query("order") order:String="desc") : Call<GithubRepositoriesResult>

}