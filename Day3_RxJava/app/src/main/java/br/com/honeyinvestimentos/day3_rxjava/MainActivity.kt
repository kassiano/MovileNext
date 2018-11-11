package br.com.honeyinvestimentos.day3_rxjava

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Flowables
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var disposable:Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameChangeObservable = RxTextView.textChangeEvents(etName)
            .skip(1)
            .debounce(800, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.text()
             }
            .toFlowable(BackpressureStrategy.LATEST)

        val yearChangeObservable = RxTextView.textChangeEvents(etYear)
            .skip(1)
            .debounce(800, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.text()
            }
            .toFlowable(BackpressureStrategy.LATEST)


        disposable = Flowables.combineLatest(
            nameChangeObservable,
            yearChangeObservable
        )
        { newName:CharSequence,
            newYear:CharSequence ->

            val nameValid = newName.length > 4
            if (!nameValid) etName.error = "Invalid name"

            val yearValid = newYear.length == 4
            if (!yearValid) etYear.error = "Invalid name"

            nameValid && yearValid
        }.subscribe { formValid:Boolean ->

            btAdd.setBackgroundColor(
                ContextCompat.getColor(applicationContext, if (formValid) R.color.colorPrimaryDark else R.color.gray)
            )

            btAdd.setTextColor(
                ContextCompat.getColor(applicationContext, if (formValid) R.color.white else R.color.black)
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}
