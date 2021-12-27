package com.androdocs.vid_photo_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.AnimationUtils
import com.androdocs.vid_photo_app.databinding.ActivityDetailsPhotoBinding
import com.androdocs.vid_photo_app.fragment.photoFragment
import com.androdocs.vid_photo_app.models.Photo


import com.squareup.picasso.Picasso
import android.app.PendingIntent.getActivity
import android.view.View
import androidx.core.view.get
import com.androdocs.vid_photo_app.R


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


        val image = intent.getStringArrayListExtra("array")
        Picasso.get().load(image!![0]).into(binding.detailiv)

        val toolbar=binding.toolbar
        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        binding.photoDet.text=image[1]
        Picasso.get().load(image[2]).into(binding.photographer)
        binding.photographername.text=image[3]


        binding.zoomin.setOnClickListener(){
            val animZoomIn = AnimationUtils.loadAnimation(this,
                R.anim.zoom_in
            )
            // assigning that animation to
            // the image and start animation
            binding.detailiv.startAnimation(animZoomIn)
        }

        binding.zoomout.setOnClickListener(){
            val animZoomOut = AnimationUtils.loadAnimation(this,
                R.anim.zoom_out
            )
            // assigning that animation to
            // the image and start animation
            binding.detailiv.startAnimation(animZoomOut)
        }




    }
}