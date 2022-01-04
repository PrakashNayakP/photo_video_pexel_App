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


class favoriteAdapter(private val favorites: List<Favorite>,val listner:onclickicon) : RecyclerView.Adapter<favoriteAdapter.ViewHolder>() {

    var lists = mutableListOf<Favorite>()

    init {
        lists = favorites as MutableList<Favorite>
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var binding = FavListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(lists[position],listner)

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


        fun bind(favorite: Favorite,listner:onclickicon) {
            Picasso.get().load(favorite.image).into(photourl);
            Picasso.get().load(favorite.image).into(photographer);
            photographername.text=favorite.name
            val arrayList: ArrayList<String> = ArrayList()


            click.setOnClickListener {
                if(favorite.type){
                    arrayList.add(favorite.url)
                    arrayList.add(favorite.desc)
                    arrayList.add(favorite.image)
                    arrayList.add(favorite.name)
                    val intent1 = Intent(click.context, detailsPhoto::class.java)
                    intent1.putExtra("array",arrayList)
                    click.context.startActivity(intent1)

                }else{
                    arrayList.add(favorite.image)
                    arrayList.add(favorite.name)
                    arrayList.add(favorite.url)
                    val intent2 = Intent(click.context, detailsVideo::class.java)
                    intent2.putExtra("array",arrayList)
                    click.context.startActivity(intent2)
                }

            }
            fav.setOnClickListener{
            listner.onFavClick(favorite)
            }




        }


    }
    interface onclickicon{
        fun onFavClick(favorite: Favorite)
    }




}