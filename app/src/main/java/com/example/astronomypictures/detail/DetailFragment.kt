package com.example.astronomypictures.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.example.astronomypictures.databinding.FragmentDetailBinding
import com.example.astronomypictures.model.Image
import com.example.astronomypictures.utility.ImageTransformer

/**
 * Dialog Fragment to display individual image
 */
class DetailFragment : DialogFragment() {

    private var imageList = ArrayList<Image>()
    private var selectedPosition: Int = 0
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewPager: ViewPager
    private lateinit var detailPagerAdapter: DetailPagerAdapter

    /**
     * Override to build your own custom Dialog container.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(LayoutInflater.from(context))
        initViews()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
    }

    /**
     * Initialises the views in the dialog fragment
     */
    private fun initViews() {
        viewPager = binding.viewPager
        imageList = arguments?.getSerializable("IMAGE_LIST") as ArrayList<Image>
        selectedPosition = requireArguments().getInt("IMAGE_POSITION")
        detailPagerAdapter = DetailPagerAdapter(context, imageList)
        viewPager.adapter = detailPagerAdapter
        viewPager.setPageTransformer(true, ImageTransformer())
        setCurrentItem(selectedPosition)
    }

    /**
     * Sets the position of the current image
     */
    private fun setCurrentItem(position: Int) {
        viewPager.setCurrentItem(position, false)
    }

}