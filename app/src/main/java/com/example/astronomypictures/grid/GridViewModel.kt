package com.example.astronomypictures.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.astronomypictures.model.Image
import com.example.astronomypictures.utility.DataUtils

/**
 * Viewmodel class associated with grid activity
 */
class GridViewModel : ViewModel() {

    private val fileName: String = "data.json"
    lateinit var imageData: String
    var imageList : List<Image> = emptyList()

    private val _images = MutableLiveData<List<Image>>()
    val image: LiveData<List<Image>>
        get() = _images

    /**
     * Called on instance of viewmodel
     */
    init {
        getNASAImages(fileName)
    }

    /**
     * Loads the list of images from json object
     */
    fun getNASAImages(fileName: String) {
        imageData = DataUtils.readJson(fileName)
        imageList = DataUtils.fromJson(imageData)
        _images.value = imageList.reversed()
    }
}