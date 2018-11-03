package br.com.honeyinvestimentos.aula1dataroom.view

import android.content.Context
import android.support.v7.widget.RecyclerView.Adapter
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.honeyinvestimentos.aula1dataroom.R
import br.com.honeyinvestimentos.aula1dataroom.model.Word

class WordListAdapter(context: Context): Adapter<WordListAdapter.WordViewHolder>() {

    val inflater = LayoutInflater.from(context)

    var words : List<Word> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return WordViewHolder(itemView)
    }

    override fun getItemCount()= words.size

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        if(words.isEmpty()){
            holder.wordItemView.text = "No words"
        }else{
            holder.wordItemView.text = words[position].word
        }

    }


    class WordViewHolder(itemView: View) : ViewHolder(itemView) {

        val wordItemView : TextView = itemView.findViewById(R.id.textView)


    }


}