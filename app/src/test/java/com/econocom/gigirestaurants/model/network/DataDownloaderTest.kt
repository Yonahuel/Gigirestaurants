package com.econocom.gigirestaurants.model.network

import com.econocom.gigirestaurants.fakes.listaFakes
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DataDownloaderTest {
    private val dataDownloader = mockk<DataDownloader>()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testDownloadRecetas() = runTest {
        coEvery { dataDownloader.downloadRestaurant() } returns flowOf(listaFakes)

        dataDownloader.downloadRestaurant().collect { restaurants ->
            assertEquals("Restaurant 1", restaurants[0].name)
            assertEquals(4, restaurants.size)
        }
    }
}