package br.com.honeyinvestimentos.day3_databinding

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import br.com.honeyinvestimentos.day3_databinding.databinding.ActivityGameInfoBinding
import br.com.honeyinvestimentos.day3_databinding.utils.SetContentView
import kotlinx.android.synthetic.main.activity_game_info.*


fun <T : ViewDataBinding> contentView(@LayoutRes layoutRes: Int) :SetContentView<T>{
    return SetContentView(layoutRes)
}

class GameInfoActivity : AppCompatActivity() {

    val games = arrayOf(
        Game("Donkey Kong",1992, "https://upload.wikimedia.org/wikipedia/pt/thumb/5/5c/Donkey_Kong.png/220px-Donkey_Kong.png",1.0),
        Game("Super Mario",2004, "https://supermariorun.com/assets/img/hero/hero_chara_mario_pc.png",1.0)
    )

    var gameIndex = 0

    val binding:ActivityGameInfoBinding by contentView(R.layout.activity_game_info)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_game_info)

        binding.game = games[gameIndex]

        btChangeGameName.setOnClickListener {

            if(gameIndex == 0)
                gameIndex =1
            else
                gameIndex =0

            binding.game  = games[gameIndex]
        }


        btRate.setOnClickListener {

            games[gameIndex].rating += 1.0

        }

    }
}
