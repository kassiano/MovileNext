package br.com.honeyinvestimentos.day3_projetohandson.ui.post

import android.arch.lifecycle.MutableLiveData
import br.com.honeyinvestimentos.day3_projetohandson.base.BaseViewModel
import br.com.honeyinvestimentos.day3_projetohandson.model.Post

class PostViewModel:BaseViewModel() {

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }


    fun getPostTitle():MutableLiveData<String> = postTitle
    fun getPostBody():MutableLiveData<String> = postBody

}