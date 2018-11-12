package br.com.honeyinvestimentos.day3_projetohandson.ui.post

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import android.view.View
import br.com.honeyinvestimentos.day3_projetohandson.R
import br.com.honeyinvestimentos.day3_projetohandson.base.BaseViewModel
import br.com.honeyinvestimentos.day3_projetohandson.model.Post
import br.com.honeyinvestimentos.day3_projetohandson.model.PostDao
import br.com.honeyinvestimentos.day3_projetohandson.network.PostApi
import br.com.honeyinvestimentos.day3_projetohandson.ui.post.adapter.PostListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao): BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val postListAdapter: PostListAdapter = PostListAdapter()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    fun loadPosts(){



        subscription =  Observable.fromCallable { postDao.all }
            .concatMap { dbPostList ->

                if(dbPostList.isEmpty()){

                    postApi.getPosts().concatMap {
                        apiPostList->
                        postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }

                }else{
                    Observable.just(dbPostList)
                }

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result->
                    onRetrievePostListSuccess(result)
                }, {
                    onRetrievePostListError()
                }
            )
    }


    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(result:List<Post>){
        Log.d("onRetrievePostList", result.size.toString())
        postListAdapter.updatePostList(result)
    }

    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}