package com.androdocs.vid_photo_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.androdocs.vid_photo_app.databinding.ActivityDetailsPhotoBinding
import com.squareup.picasso.Picasso
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.adapter.photoAdapter
import com.androdocs.vid_photo_app.roomdb.*


class detailsPhoto() : AppCompatActivity() {

    //creating variable for binding
    private lateinit var binding:ActivityDetailsPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Vid_photo_app)

        //inflating binding
        binding= ActivityDetailsPhotoBinding.inflate(layoutInflater)

        //setting the contentview to binding.root
        setContentView(binding.root)

        //hiding toolbar
        supportActionBar?.hide()


        val unfav=binding.unfav
        val fav=binding.faviv


        val image = intent.getStringArrayListExtra("array")
        Picasso.get().load(image!![0]).into(binding.detailiv)

        if(image[4]=="true"){
            fav.visibility= View.VISIBLE
            unfav.visibility= View.GONE
        }else{
            fav.visibility= View.GONE
            unfav.visibility= View.VISIBLE
        }



        val toolbar=binding.toolbar
        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }

        binding.photoDet.text=image[1]
        Picasso.get().load(image[2]).into(binding.photographer)
        binding.photographername.text=image[3]

//        val dao = FavoriteDatabase.getInstance(this).getFavoritesDao
//        val repository = FavoriteRepository(dao)
//        val factory = FavoriteViewModalFactory(repository)
//        val viewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)
//
//
//
//        val link:String=image[0]
//        val name: String = image[3]
//        val img: String = image[2]
//        val desc: String = image[1]
//        val favorites = Favorite(link, name, true, img, desc)
//
//
//        unfav.setOnClickListener {
//            viewModel.addFavorite(favorites)
////            isInRoom.add(link)
////            val isSaved=saveArray()
////            Log.d("Saved:$isSaved", "added successfully$isInRoom"))
//            unfav.visibility=View.GONE
//            fav.visibility=View.VISIBLE
//        }
//
//        //when he unchecks the heart
//        fav.setOnClickListener{
//            viewModel.deleteFavorite(favorites)
////            isInRoom.remove(link)
////            val isSaved=saveArray()
////            Log.d("Saved:$isSaved", "deleted successfully$isInRoom")
//            fav.visibility=View.GONE
//            unfav.visibility=View.VISIBLE
//        }



        binding.zoomin.setOnClickListener{
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