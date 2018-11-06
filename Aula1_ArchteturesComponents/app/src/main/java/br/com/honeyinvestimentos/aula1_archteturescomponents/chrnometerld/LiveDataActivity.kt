package br.com.honeyinvestimentos.aula1_archteturescomponents.chrnometerld

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.honeyinvestimentos.aula1_archteturescomponents.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(LiveDataTimerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        subscribe()
    }

    private fun subscribe() {
        val elapsetTiemObserver = Observer<Long> {
            time->
            tvTimer.text = "$time segundos se passaram"
        }

        viewModel.elapsedTime.observe(this, elapsetTiemObserver)
    }

}
