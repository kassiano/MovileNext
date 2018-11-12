package br.com.honeyinvestimentos.day3_projetohandson.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.honeyinvestimentos.day3_projetohandson.R
import br.com.honeyinvestimentos.day3_projetohandson.databinding.ActivityPostListBinding
import br.com.honeyinvestimentos.day3_projetohandson.di.ViewModelFactory

class PostListActivity: AppCompatActivity(){

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)

        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        viewModel = ViewModelProviders
                    .of(this, ViewModelFactory(this))
                    .get(PostListViewModel::class.java)


        viewModel.errorMessage.observe(this, Observer {
            errorMessage->
            if(errorMessage!= null){
                showError(errorMessage)
            }else{
                hideError()
            }
        })

        binding.viewModel = viewModel
    }


    private fun showError(@StringRes errorMessage: Int) {

        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.retry, viewModel.errorClickListener)
            show()
        }
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

}