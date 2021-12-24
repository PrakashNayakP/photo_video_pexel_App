package com.androdocs.vid_photo_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.androdocs.vid_photo_app.databinding.ActivityDetailsPhotoBinding
import com.androdocs.vid_photo_app.fragment.photoFragment
import com.androdocs.vid_photo_app.models.Photo


import com.squareup.picasso.Picasso

class detailsPhoto : AppCompatActivity() {

    //creating variable for binding
    private lateinit var binding:ActivityDetailsPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflating binding
        binding= ActivityDetailsPhotoBinding.inflate(layoutInflater)

        //setting the contentview to binding.root
        setContentView(binding.root)

        //hiding toolbar
        getSupportActionBar()?.hide()

        val image = intent.getParcelableExtra<Photo>(photoFragment.photodet)
        Picasso.get().load(image?.src?.large).into(binding.detailiv)

        binding.zoomin.setOnClickListener(){
            val animZoomIn = AnimationUtils.loadAnimation(this,
                R.anim.zoom_in)
            // assigning that animation to
            // the image and start animation
            binding.detailiv.startAnimation(animZoomIn)
        }

        binding.zoomout.setOnClickListener(){
            val animZoomOut = AnimationUtils.loadAnimation(this,
                R.anim.zoom_out)
            // assigning that animation to
            // the image and start animation
            binding.detailiv.startAnimation(animZoomOut)
        }




    }
}