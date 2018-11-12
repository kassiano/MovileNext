package br.com.honeyinvestimentos.day3_projetohandson.ui.post.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.honeyinvestimentos.day3_projetohandson.R
import br.com.honeyinvestimentos.day3_projetohandson.databinding.ItemPostBinding
import br.com.honeyinvestimentos.day3_projetohandson.model.Post
import br.com.honeyinvestimentos.day3_projetohandson.ui.post.PostViewModel

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private lateinit var postList: List<Post>


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {

        val binding: ItemPostBinding = DataBindingUtil.inflate(
                LayoutInflater
                    .from(parent.context), R.layout.item_post, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) {
            postList.size
        } else {
            0
        }
    }

    fun updatePostList(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }


    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(post: Post) {

            viewModel.bind(post)

            binding.viewModel = viewModel

        }
    }
}