package br.com.honeyinvestimentos.aula1dataroom.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.honeyinvestimentos.aula1dataroom.R
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_REPLY = "wordlistsql.reply"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        btSave.setOnClickListener {

            val replyIntent = Intent()

            if(etWord.text.isEmpty()){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{

                val word = etWord.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
            }

            finish()
        }

    }
}
