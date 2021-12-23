package com.androdocs.vid_photo_app.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.databinding.PhotoListBinding
import com.androdocs.vid_photo_app.models.Photo
import com.squareup.picasso.Picasso


class photoAdapter(private val photolist:List<Photo>) : RecyclerView.Adapter<photoAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = PhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(photolist[position])

    }

    override fun getItemCount(): Int {
        return photolist.size
    }

    class ViewHolder(binding: PhotoListBinding) :RecyclerView.ViewHolder(binding.root)
    {
        val photourl=binding.photo
        val photographer=binding.photographer
        val photographername=binding.photographername
        fun bind(photo: Photo) {
            Picasso.get().load(photo.src.landscape).into(photourl);
            Picasso.get().load(photo.photographer_url).into(photographer);
            photographername.text=photo.photographer

        }


    }
}