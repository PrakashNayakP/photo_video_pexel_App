package com.androdocs.vid_photo_app.ui

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
        binding.videoDet.text = video!![2]
        Picasso.get().load(video[0]).into(binding.photographer)
        binding.photographername.text=video!![2]

//        controlVideo(video[3])

        var simpleVideoView: VideoView = binding.videoView
        simpleVideoView.setVideoPath(video[3])
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



