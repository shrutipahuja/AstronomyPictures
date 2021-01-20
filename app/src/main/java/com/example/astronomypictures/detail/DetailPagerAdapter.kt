package com.example.astronomypictures.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.astronomypictures.R
import com.example.astronomypictures.model.Image
import kotlinx.android.synthetic.main.layout_image.view.*

/**
 * Pager Adapter used to inflate the image
 */
class DetailPagerAdapter(
    private val context: Context?,
    private val imageList: ArrayList<Image>
) : PagerAdapter() {

    /**
     * Create the page for the given position.
     */
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.layout_image, container, false)
        val image = imageList.get(position)

        Glide.with(context)
            .load(image.url)
            .apply(RequestOptions().centerCrop().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image))
            .into(view.image_content)
        view.title_label.text = image.title
        view.explanation_label.text = image.explanation
        view.date_label.text = context.getString(R.string.date_s, image.date)
        if (image.copyright != null) {
            view.copyright_label.visibility = View.VISIBLE
            view.copyright_label.text = context.getString(R.string.copyright_string, image.copyright)
        }

        container.addView(view)
        return view
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return imageList.size
    }

    /**
     * Determines whether a page View is associated with a specific key object
     */
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj as View
    }

    /**
     * Remove a page for the given position.
     */
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}