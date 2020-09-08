package com.example.music

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //seekbar position should change while playing
    //we'll create a runnable object and a handler for this
    lateinit var runnable:Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating a new media player object
        val mediaplayer =  MediaPlayer.create(this,R.raw.music2)

        //seekbar functionalities
        seekbar.progress = 0
        seekbar.max = mediaplayer.duration

        //play button event
        play_btn.setOnClickListener{
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                play_btn.setImageResource(R.drawable.ic_pause_black_24dp)
            }else{
                mediaplayer.pause()
                play_btn.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            }
        }
        //seekbar event
        //song must change according to seekbar position
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(pos)

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        //let's code the functionality of seekbar while playing
        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
        //when music finishes,seekbar should get to 0 and button should change
        mediaplayer.setOnCompletionListener {
            play_btn.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            seekbar.progress = 0
        }
    }
}
