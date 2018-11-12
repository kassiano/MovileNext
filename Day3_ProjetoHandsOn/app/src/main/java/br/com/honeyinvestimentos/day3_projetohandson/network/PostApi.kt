package br.com.honeyinvestimentos.day3_projetohandson.network

import br.com.honeyinvestimentos.day3_projetohandson.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}