package com.androdocs.vid_photo_app.ui

import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.Toast
import android.widget.VideoView
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.databinding.ActivityDetailsVideoBinding
import com.androdocs.vid_photo_app.fragment.videoFragment
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.models.VideoFile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_video.*

class detailsVideo : AppCompatActivity() {
    //creating variable for binding
    private lateinit var binding: ActivityDetailsVideoBinding


     private var mp:MediaPlayer?=null


//    // declaring a null variable for VideoView
//    var simpleVideoView: VideoView? =null


    // declaring a null variable for MediaController
    var mediaControls: MediaController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Vid_photo_app)
        //inflating binding
        binding = ActivityDetailsVideoBinding.inflate(layoutInflater)

        //setting the contentview to binding.root
        setContentView(binding.root)

        //hiding toolbar
        getSupportActionBar()?.hide()

        val toolbar=binding.toolbar
        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

//        val video=intent.getParcelableExtra<Video>("videodet")
        val intent = intent
        val video = intent.getStringArrayListExtra("array")
        binding.videoDet.text = video!![1]
        Picasso.get().load(video[0]).into(binding.photographer)
        binding.photographername.text=video!![1]


        val unfav=binding.unfav
        val fav=binding.faviv

        if(video[3]=="true"){
            fav.visibility= View.VISIBLE
            unfav.visibility= View.GONE
        }else{
            fav.visibility= View.GONE
            unfav.visibility= View.VISIBLE
        }



//        controlVideo(video[3])

        var simpleVideoView: VideoView = binding.videoView
        simpleVideoView.setVideoPath(video[2])
//        val mediaController : MediaController
//        mediaController = MediaController(this)
//        mediaController.setAnchorView(videoView)
//
//        videoView.setMediaController(mediaController)

        simpleVideoView.start()

        simpleVideoView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
            binding.seekBar.setMax(simpleVideoView.getDuration());
            initializeSeekbar(simpleVideoView)

        })
        binding.playpause.setOnClickListener {
            if (simpleVideoView.isPlaying()) {
                simpleVideoView.pause();
                binding.playpause.setImageDrawable(getResources().getDrawable(R.drawable.play));

            } else {
                simpleVideoView.start();
                binding.seekBar.setProgress(simpleVideoView.getCurrentPosition())
                binding.playpause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
            }


        }

        // reference to change the orientation on every click
        var isPortrait = true

        // Button action on click
        binding.maximize.setOnClickListener {
            // if isPortrait true, change to Landscape
            requestedOrientation = if (isPortrait) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                // else change to Portrait
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }

            // opposite the value of isPortrait
            isPortrait = !isPortrait
        }


    }



    private fun initializeSeekbar(simpleVideoView:VideoView) {
        binding.seekBar.max=simpleVideoView!!.duration

        val handler=Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try {
                    binding.seekBar.progress = simpleVideoView!!.currentPosition
                    handler.postDelayed(this, 100)
                }
                catch (e:Exception){
                    binding.seekBar.progress=0
                }
            }

        },0)
    }

    }



