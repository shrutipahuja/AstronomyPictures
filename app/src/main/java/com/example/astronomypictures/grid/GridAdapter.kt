package com.example.astronomypictures.grid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.astronomypictures.R
import com.example.astronomypictures.databinding.LayoutGridImageBinding
import com.example.astronomypictures.model.Image

/**
 * Recyclerview adapter used to pass the images to recyclerview
 */
class GridAdapter(
    private val imageList: List<Image>,
    private val imageClickListener: ImageClickListener
) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    var loadingComplete = false

    /**
     * This method is used to create a new viewholder and initializes some private fields
     * to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutGridImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false).root
        return GridViewHolder(view)
    }

    /**
     * Returns the size of the image list
     */
    override fun getItemCount(): Int {
        return imageList.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val image = imageList[position]
        val options: RequestOptions = RequestOptions().centerCrop().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)
        holder.layoutGridImageBinding.apply {
            apodImage.apply {
                Glide.with(context).load(image.url)
                    .apply(options).into(this)
                loadingComplete = true
            }
        }
        holder.layoutGridImageBinding.apodImage.setOnClickListener {
            if (loadingComplete) {
                imageClickListener.onClick(imageList, position)
            }
        }
    }

    /**
     * Custom view holder class to inflate individual image item
     */
    inner class GridViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            var layoutGridImageBinding: LayoutGridImageBinding = LayoutGridImageBinding.bind(itemView)
    }

    /**
     * Interface used for onclick method
     */
    interface ImageClickListener {
        fun onClick(images: List<Image>, position: Int)
    }
}
