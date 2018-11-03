package br.com.honeyinvestimentos.aula1_kotlin.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.honeyinvestimentos.aula1_kotlin.R
import br.com.honeyinvestimentos.aula1_kotlin.api.Repository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repository_lang_item.view.*

class RepositoryAdapter(
        private val items: List<Repository>,
        private val context:Context,
        private val listenner: (Repository) -> Unit) :
        Adapter<RepositoryAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(context)
                .inflate(R.layout.repository_lang_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listenner)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(item:Repository, listenner: (Repository)->Unit)=
                with(itemView){


                    Glide.with(context)
                            .load(item.owner.avatar_url)
                            .into(ivMain)

                    /*
                    Picasso.get()
                            .load("https://upload.wikimedia.org/wikipedia/commons/b/b5/Kotlin-logo.png")
                            .into(ivMain)
                               */

                    //ivMain.setImageDrawable(ContextCompat.getDrawable(context,item.imageResourceId))
                    tvTitle.text = item.name
                    tvOwner.text = item.owner.login
                    tvDescription.text = item.description

                    setOnClickListener { listenner(item) }
                }
    }
}