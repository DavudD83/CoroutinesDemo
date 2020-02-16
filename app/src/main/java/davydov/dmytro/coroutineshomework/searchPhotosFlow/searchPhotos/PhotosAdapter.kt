package davydov.dmytro.coroutineshomework.searchPhotosFlow.searchPhotos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import davydov.dmytro.coroutineshomework.R

class PhotosAdapter : RecyclerView.Adapter<PhotoViewHolder>() {
    private val photos = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.item_photo, parent, false)

        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        photos[position].let { holder.bind(it) }
    }

    fun updatePhotos(newPhotos: List<Photo>) {
        photos.run {
            clear()
            addAll(newPhotos)
        }
        notifyDataSetChanged()
    }
}

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val photoImage = view.findViewById<ImageView>(R.id.photo)
    private val likesCount = view.findViewById<TextView>(R.id.likesCount)

    fun bind(photo: Photo) {
        Glide
            .with(photoImage)
            .run {
                clear(photoImage)

                load(photo.url)
                    .centerCrop()
                    .into(photoImage)
            }

        likesCount.text = "${photo.likesCount}"
    }
}