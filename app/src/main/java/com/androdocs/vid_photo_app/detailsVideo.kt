package com.androdocs.vid_photo_app

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.androdocs.vid_photo_app.databinding.ActivityDetailsPhotoBinding
import com.androdocs.vid_photo_app.databinding.ActivityDetailsVideoBinding
import com.androdocs.vid_photo_app.fragment.videoFragment
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.models.VideoFile

class detailsVideo : AppCompatActivity() {
    //creating variable for binding
    private lateinit var binding: ActivityDetailsVideoBinding

    // declaring a null variable for VideoView
    var simpleVideoView: VideoView? =null


    // declaring a null variable for MediaController
    var mediaControls: MediaController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflating binding
                binding= ActivityDetailsVideoBinding.inflate(layoutInflater)

        //setting the contentview to binding.root
        setContentView(binding.root)

        //hiding toolbar
        getSupportActionBar()?.hide()

        val video=intent.getParcelableExtra<Video>(videoFragment.videodet)
          binding.textView.text=video?.user?.name



        simpleVideoView = binding.videoView
        if(mediaControls == null){
            // creating an object of media controller class
            mediaControls = MediaController(this)

            // set the anchor view for the video view
            mediaControls!!.setAnchorView(this.simpleVideoView)
        }
        // set the media controller for video view
        simpleVideoView!!.setMediaController(mediaControls)

        // set the absolute path of the video file which is going to be played
        simpleVideoView!!.setVideoURI(
            Uri.parse("" + video?.url))

        simpleVideoView!!.requestFocus()

        // starting the video
        simpleVideoView!!.start()

        // display a toast message
        // after the video is completed
        simpleVideoView!!.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video completed",
                Toast.LENGTH_LONG).show()
        }

        // display a toast message if any
        // error occurs while playing the video
        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occured " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }
    }
}
