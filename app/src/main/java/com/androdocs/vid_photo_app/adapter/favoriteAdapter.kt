package com.androdocs.vid_photo_app.adapter



import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.databinding.FavListBinding
import com.androdocs.vid_photo_app.databinding.PhotoListBinding
import com.androdocs.vid_photo_app.models.Photo
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.roomdb.Favorite
import com.androdocs.vid_photo_app.ui.detailsPhoto
import com.androdocs.vid_photo_app.ui.detailsVideo
import com.squareup.picasso.Picasso


class favoriteAdapter(private val favorites: List<Favorite>) : RecyclerView.Adapter<favoriteAdapter.ViewHolder>() {

    var lists = mutableListOf<Favorite>()

    init {
        lists = favorites as MutableList<Favorite>
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = FavListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(lists[position])

    }

    override fun getItemCount(): Int {
        return lists.size
    }

    class ViewHolder(binding: FavListBinding) :RecyclerView.ViewHolder(binding.root)
    {
        val photourl=binding.photo
        val photographer=binding.photographer
        val photographername=binding.photographername
        val click=binding.root
        val fav=binding.faviv


        fun bind(favorite: Favorite) {
            Picasso.get().load(favorite.image).into(photourl);
            Picasso.get().load(favorite.image).into(photographer);
            photographername.text=favorite.name


        }


    }



}