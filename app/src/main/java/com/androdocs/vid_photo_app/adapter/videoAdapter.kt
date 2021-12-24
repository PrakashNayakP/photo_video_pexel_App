package com.androdocs.vid_photo_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.databinding.VideoListBinding
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.models.VideoFile
import com.squareup.picasso.Picasso

class videoAdapter(private  val videolist:List<Video>,val listner:(Video)->Unit):RecyclerView.Adapter<videoAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = VideoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(videolist[position],listner)

    }

    override fun getItemCount(): Int {
        return videolist.size
    }

    class ViewHolder(binding: VideoListBinding) :RecyclerView.ViewHolder(binding.root)
    {
        val photourl=binding.photo
        val photographer=binding.photographer
        val photographername=binding.photographername
        fun bind(video: Video,listner: (Video) -> Unit) {
            Picasso.get().load(video.image).into(photourl);
//            Picasso.get().load(video.user.url).into(photographer);
            Picasso.get().load(video.image).into(photographer);
            photographername.text=video.user.name
            val str:String=video.user.name

            itemView.setOnClickListener{listner(video)

            }

        }


    }
}