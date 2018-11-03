package br.com.honeyinvestimentos.aula1_archteturescomponents.seekbar


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import br.com.honeyinvestimentos.aula1_archteturescomponents.R
import kotlinx.android.synthetic.main.fragment_seek_bar.*


class SeekBarFragment : Fragment() {

    lateinit var viewModel :SeekBarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_seek_bar, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel =  ViewModelProviders.of(it).get(SeekBarViewModel::class.java)

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                    if(fromUser){
                        viewModel.seekBarValue.value = progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            })


            viewModel.seekBarValue.observe(this, Observer{
                progress->

                seekBar.progress = progress?:0
            })
        }

    }

}
