package com.example.astronomypictures.grid

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule

class GridViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GridViewModel

    @Before
    fun setup() {
        viewModel = GridViewModel()
    }

    /**
     * Check if data returned is non-null
     * with valid input json file
     */
    @Test
    fun `instantiate data with valid json file, returns non-null`() {
        viewModel.getNASAImages("data.json")
        assertNotNull(viewModel.imageList)
    }

    /**
     * Check if data returned is blank
     * with empty input json file
     */
    @Test
    fun `instantiate data with invalid json file, returns null`() {
        viewModel.getNASAImages("testdata.json")
        assertTrue(viewModel.imageList.get(0).title.isBlank())
    }

}