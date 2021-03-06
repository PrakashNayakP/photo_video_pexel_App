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
import android.app.Activity
import android.content.Context

import android.content.SharedPreferences








class photoFragment(private val query:String) : Fragment(), photoAdapter.onclickicon {

     private var isInRoom = arrayListOf<String>()
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


    override fun onItemClick(photo:Photo,isFav:Boolean) {

        val dao = FavoriteDatabase.getInstance(this.requireContext()).getFavoritesDao
        val repository = FavoriteRepository(dao)
        val factory = FavoriteViewModalFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(FavoriteViewModal::class.java)
        val link:String=photo.src.large
            val name: String = photo.photographer
            val image: String = photo.src.landscape
            val desc: String = photo.alt
//        val link:String=arrayList[0]
//        val name: String = arrayList[3]
//        val image: String = arrayList[2]
//        val desc: String = arrayList[1]
            val favorites = Favorite(link, name, true, image, desc)
        if(!isFav) {
            viewModel.addFavorite(favorites)
            isInRoom.add(link)
            val isSaved=saveArray()
            Log.d("Saved:$isSaved", "added successfully$isInRoom")
        }
        else{
            viewModel.deleteFavorite(favorites)
            isInRoom.remove(link)
             val isSaved=saveArray()
            Log.d("Saved:$isSaved", "deleted successfully$isInRoom")

        }


    }

    override fun isInDatabase(url: String): Boolean {
         isInRoom=getArray()
        Log.d("checking", "$url is in array ${isInRoom!!.contains(url)}")
        return isInRoom!!.contains(url)
    }


    fun saveArray(): Boolean {
        val sp = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val mEdit1 = sp.edit()
        val set: MutableSet<String> = HashSet()
        set.addAll(isInRoom)
        mEdit1.putStringSet("list", set)
        return mEdit1.commit()
    }

    private fun getArray(): ArrayList<String> {
        val sp = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        //NOTE: if shared preference is null, the method return empty Hashset and not null
        val set = sp.getStringSet("list", HashSet())
        return ArrayList(set)
    }







}
