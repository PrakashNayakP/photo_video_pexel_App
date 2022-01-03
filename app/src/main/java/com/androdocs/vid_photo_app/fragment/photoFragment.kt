package com.androdocs.vid_photo_app.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.androdocs.vid_photo_app.MainActivity
import com.androdocs.vid_photo_app.databinding.FragmentPhotoBinding
import com.androdocs.vid_photo_app.api.retrofitClient
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.adapter.favoriteAdapter
import com.androdocs.vid_photo_app.adapter.photoAdapter
import com.androdocs.vid_photo_app.adapter.videoAdapter
import com.androdocs.vid_photo_app.databinding.FragmentFavoriteBinding
import com.androdocs.vid_photo_app.databinding.PhotoListBinding
import com.androdocs.vid_photo_app.models.Photo
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.ui.detailsPhoto
import com.androdocs.vid_photo_app.models.photoresponse
import com.androdocs.vid_photo_app.roomdb.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class photoFragment(private val query:String) : Fragment(), photoAdapter.onclickicon {


    // TODO: Rename and change types of parameters
    private var _binding: FragmentPhotoBinding? = null
//    private var _favBinding: FragmentFavoriteBinding? =null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
//    private val favBinding get() = _favBinding!!





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
//        _favBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view= binding.root


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call= retrofitClient.instance.getSearchImage(query)
        call.enqueue(object : Callback<photoresponse> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<photoresponse>, response: Response<photoresponse>) {
                if (response.code() == 200) {
                    val photoresponse: photoresponse = response.body()!!
                    Log.d("value",photoresponse.toString())
                    binding.recyclerView1.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(activity,1)

                        adapter = photoAdapter(photoresponse.photos,this@photoFragment)
                        binding.emptyView.visibility=View.GONE
                    }
                }
                else{
                    val photoresponse: photoresponse = response.body()!!
                    Log.d("value","error")
                }
            }

            override fun onFailure(call: Call<photoresponse> , t: Throwable) {
                // textView.setText(t.message)
                Toast.makeText(activity,"Something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onItemClick(photo: Photo,isFav:Boolean) {

        val dao = FavoriteDatabase.getInstance(this.requireContext()).getFavoritesDao
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModalFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)

        val link:String=photo.src.large
            val name: String = photo.photographer
            val image: String = photo.src.landscape
            val desc: String = photo.alt
            val favorites = Favorite(link, name, true, image, desc)
        if(!isFav) {
            viewModel.addFavorite(favorites)
            Log.d("Success", "added successfully$favorites")
        }
        else{
            viewModel.deleteFavorite(favorites)
            Log.d("Success", "deleted successfully$favorites")
        }


    }

    override fun isInDatabase(url: String): Boolean {
        val dao = FavoriteDatabase.getInstance(this.requireContext()).getFavoritesDao
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModalFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)
        viewModel.isRecordExists(url)
        return viewModel.isThere
    }



}
