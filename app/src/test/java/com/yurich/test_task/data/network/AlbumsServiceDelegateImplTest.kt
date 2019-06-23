package com.yurich.test_task.data.network

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.stub
import com.yurich.test_task.data.repository.Album
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class AlbumsServiceDelegateImplTest {

    @Mock
    lateinit var service: AlbumsService

    lateinit var delegate: AlbumsServiceDelegateImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        delegate = AlbumsServiceDelegateImpl(service)
    }

    @Test
    fun `getAlbums return non-empty values`() {
        val id = 1
        val userId = 123
        val title = "asdf"
        runBlocking {
            service.stub {
                onBlocking { getAlbums() } doReturn Response.success(listOf(NetworkAlbum(id, title, userId)))
            }

            val albums = delegate.getAlbums()

            assertEquals(listOf(Album(userId, id, title)), albums)
        }
    }

    @Test
    fun `getAlbums return empty values`() {
        runBlocking {
            service.stub {
                onBlocking { getAlbums() } doReturn Response.success(listOf())
            }

            val albums = delegate.getAlbums()

            assertEquals(listOf<Album>(), albums)
        }
    }

}