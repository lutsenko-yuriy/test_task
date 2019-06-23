package com.yurich.test_task.data.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.yurich.test_task.data.database.AlbumsDaoDelegate
import com.yurich.test_task.data.network.AlbumsServiceDelegate
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class AlbumsRepositoryImplTest {

    @Mock
    lateinit var serviceDelegate: AlbumsServiceDelegate

    @Mock
    lateinit var daoDelegate: AlbumsDaoDelegate

    lateinit var repository: AlbumsRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = AlbumsRepositoryImpl(serviceDelegate, daoDelegate)
    }

    @Test
    fun `refreshAlbumsData returns data from network`() {
        val returnList = listOf(
            Album(12, 1, "Title 1"),
            Album(10, 2, "Title 2"),
            Album(11234, 4, "Title 3")
        )
        runBlocking {
            serviceDelegate.stub {
                onBlocking { getAlbums() } doReturn returnList
            }

            val dataState = repository.refreshAlbumsData()

            assertThat(dataState, instanceOf(AlbumsDataState.FromNetwork::class.java))
            assertEquals(returnList, (dataState as AlbumsDataState.FromNetwork).albums)

            verify(serviceDelegate, times(1)).getAlbums()
            verify(daoDelegate, times(1)).refreshAlbums(returnList)
        }
    }


    @Test
    fun `refreshAlbumsData returns data from database`() {
        val returnList = listOf(
            Album(12, 1, "Title 1"),
            Album(10, 2, "Title 2"),
            Album(11234, 4, "Title 3")
        )
        runBlocking {
            serviceDelegate.stub {
                onBlocking { getAlbums() } doReturn emptyList()
            }
            daoDelegate.stub {
                onBlocking { getAlbums() } doReturn returnList
            }

            val dataState = repository.refreshAlbumsData()


            assertThat(dataState, instanceOf(AlbumsDataState.FromDatabase::class.java))
            assertEquals(returnList, (dataState as AlbumsDataState.FromDatabase).albums)

            verify(serviceDelegate, times(1)).getAlbums()
            verify(daoDelegate, times(1)).getAlbums()
        }
    }

    @Test
    fun `refreshAlbumsData returns error`() {
        runBlocking {
            serviceDelegate.stub {
                onBlocking { getAlbums() } doReturn emptyList()
            }
            daoDelegate.stub {
                onBlocking { getAlbums() } doReturn emptyList()
            }

            val dataState = repository.refreshAlbumsData()

            assertEquals(dataState, AlbumsDataState.FromNowhere)

            verify(serviceDelegate, times(1)).getAlbums()
            verify(daoDelegate, times(1)).getAlbums()
        }
    }

}