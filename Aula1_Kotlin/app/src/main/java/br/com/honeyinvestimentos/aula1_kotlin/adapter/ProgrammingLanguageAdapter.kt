package br.com.honeyinvestimentos.aula1_kotlin.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.honeyinvestimentos.aula1_kotlin.R
import br.com.honeyinvestimentos.aula1_kotlin.model.ProgrammingLanguage
import kotlinx.android.synthetic.main.programming_lang_item.view.*

class ProgrammingLanguageAdapter(
        private val items: List<ProgrammingLanguage>,
        private val context:Context,
        private val listenner: (ProgrammingLanguage) -> Unit) :
        Adapter<ProgrammingLanguageAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater
                .from(context)
                .inflate(R.layout.programming_lang_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listenner)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(item:ProgrammingLanguage, listenner: (ProgrammingLanguage)->Unit)=
                with(itemView){

                    ivMain.setImageDrawable(ContextCompat.getDrawable(context,item.imageResourceId))
                    tvTitle.text = item.title
                    tvLaunchYear.text = item.year.toString()
                    tvDescription.text = item.description

                    setOnClickListener { listenner(item) }
                }
    }
}