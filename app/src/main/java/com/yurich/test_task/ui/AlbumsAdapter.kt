package com.yurich.test_task.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yurich.test_task.R
import com.yurich.test_task.data.repository.Album
import kotlinx.android.synthetic.main.single_album_layout.view.*

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    private val albums = mutableListOf<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_album_layout, parent, false))
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    fun updateAlbumsList(newAlbums: List<Album>) {
        val oldAlbums = this.albums

        val diff = DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    oldAlbums[oldItemPosition].id == newAlbums[newItemPosition].id

                override fun getOldListSize() = oldAlbums.size
                override fun getNewListSize() = newAlbums.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    oldAlbums[oldItemPosition] == newAlbums[newItemPosition]

            }
        )

        this.albums.clear()
        this.albums.addAll(newAlbums)

        diff.dispatchUpdatesTo(this)
    }


    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(album: Album) = with(itemView) {
            album_title.text = album.title
            album_description.text = resources.getText(R.string.album_description, album.userId.toString())
        }

    }

}