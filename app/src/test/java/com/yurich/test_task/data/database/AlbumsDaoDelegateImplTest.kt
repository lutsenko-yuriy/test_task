package com.yurich.test_task.data.database

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.yurich.test_task.data.repository.Album
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AlbumsDaoDelegateImplTest {

    @Mock
    lateinit var dao: AlbumsDao

    lateinit var delegate: AlbumsDaoDelegateImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        delegate = AlbumsDaoDelegateImpl(dao)
    }

    @Test
    fun getAlbums() {
        val id = 1
        val userId = 2
        val title = "afsdf"
        runBlocking {
            dao.stub {
                onBlocking { getAlbums() } doReturn listOf(LocalStorageAlbum(id, userId, title))
            }

            assertEquals(listOf(Album(userId, id, title)), delegate.getAlbums())
        }
    }

}