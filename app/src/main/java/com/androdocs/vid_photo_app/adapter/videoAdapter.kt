package com.androdocs.vid_photo_app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androdocs.vid_photo_app.R
import com.androdocs.vid_photo_app.databinding.VideoListBinding
import com.androdocs.vid_photo_app.models.Video
import com.androdocs.vid_photo_app.models.VideoFile
import com.androdocs.vid_photo_app.ui.detailsVideo
import com.squareup.picasso.Picasso

class videoAdapter(private  val videolist:List<Video>,val listner:onclickicon):RecyclerView.Adapter<videoAdapter.ViewHolder>(){


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
        val fav=binding.faviv
        val click=binding.root


        fun bind(video: Video,listner:onclickicon) {
            Picasso.get().load(video.image).into(photourl);
//            Picasso.get().load(video.user.url).into(photographer);
            Picasso.get().load(video.image).into(photographer);
            photographername.text=video.user.name
//            val str:String=video.user.name

            //----------------------------------------------

            val arrayList: ArrayList<String> = ArrayList()
            arrayList.add(video.image)
            arrayList.add(video.user.url)
            arrayList.add(video.user.name)
            arrayList.add(video.video_files[1].link)
            val intent = Intent(click.context, detailsVideo::class.java)
            click.setOnClickListener {
                intent.putExtra("array",arrayList)
                click.context.startActivity(intent)
            }
            fav.setOnClickListener {
                listner.onItemClick(video)
                fav.setBackgroundResource(R.drawable.favemoji2)
            }


//            click.setOnClickListener{
//                listner(video)
//            }

        }


    }


    interface onclickicon{
        fun onItemClick(video: Video)
    }


}