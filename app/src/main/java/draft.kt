//import android.widget.VideoView
//import com.androdocs.vid_photo_app.R
//
////        simpleVideoView = binding.videoView
//var simpleVideoView : VideoView = binding.videoView
//simpleVideoView.setVideoPath(video[3])
////        val mediaController : MediaController
////        mediaController = MediaController(this)
////        mediaController.setAnchorView(videoView)
////
////        videoView.setMediaController(mediaController)
//
//simpleVideoView.start()
//
//simpleVideoView.setOnPreparedListener(MediaPlayer.OnPreparedListener {
//    binding.seekBar.setMax(simpleVideoView.getDuration());
//
//})
//binding.playpause.setOnClickListener {
//    if(simpleVideoView.isPlaying()){
//        simpleVideoView.pause();
//        binding.playpause.setImageDrawable(getResources().getDrawable(R.drawable.play));
//
//    }
//    else{
//        simpleVideoView.start();
//        binding.seekBar.setProgress(simpleVideoView.getCurrentPosition())
//        binding.playpause.setImageDrawable(getResources().getDrawable(R.drawable.pause));
//    }
//
//
//
//}
//
//
//
//
////        if(mediaControls == null){
////            // creating an object of media controller class
////            mediaControls = MediaController(this)
////
////            // set the anchor view for the video view
////            mediaControls!!.setAnchorView(this.simpleVideoView)
////        }
////        // set the media controller for video view
////        simpleVideoView!!.setMediaController(mediaControls)
////
////        // set the absolute path of the video file which is going to be played
////        simpleVideoView!!.setVideoURI(
//////            Uri.parse("" + video!!.video_files[2]?.link))
////            Uri.parse("" + video!![3]))
////
////        simpleVideoView!!.requestFocus()
////
////        // starting the video
////        simpleVideoView!!.start()
////
////        // display a toast message
////        // after the video is completed
////        simpleVideoView!!.setOnCompletionListener {
////            Toast.makeText(applicationContext, "Video completed",
////                Toast.LENGTH_LONG).show()
////        }
////
////        // display a toast message if any
////        // error occurs while playing the video
////        simpleVideoView!!.setOnErrorListener { mp, what, extra ->
////            Toast.makeText(applicationContext, "An Error Occured " +
////                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
////            false
//        }
