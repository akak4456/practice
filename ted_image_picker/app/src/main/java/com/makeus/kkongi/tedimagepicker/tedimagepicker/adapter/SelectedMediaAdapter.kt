package com.makeus.kkongi.tedimagepicker.tedimagepicker.adapter

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bumptech.glide.Glide
import com.makeus.kkongi.tedimagepicker.R
import com.makeus.kkongi.tedimagepicker.databinding.ItemSelectedMediaBinding
import com.makeus.kkongi.tedimagepicker.tedimagepicker.base.BaseRecyclerViewAdapter
import com.makeus.kkongi.tedimagepicker.tedimagepicker.base.BaseViewHolder

internal class SelectedMediaAdapter :
    BaseRecyclerViewAdapter<Uri, SelectedMediaAdapter.MediaViewHolder>() {

    var onClearClickListener: ((Uri) -> Unit)? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun getViewHolder(parent: ViewGroup, viewType: ViewType) = MediaViewHolder(parent)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutManager = recyclerView.layoutManager
    }


    inner class MediaViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemSelectedMediaBinding, Uri>(parent, R.layout.item_selected_media) {

        init {
            binding.ivClear.setOnClickListener {
                val item = getItem(adapterPosition.takeIf { it != NO_POSITION }
                    ?: return@setOnClickListener)
                onClearClickListener?.invoke(item)
            }
        }

        override fun bind(data: Uri) {
            Log.d("ted", "MediaViewHolder: $adapterPosition")
            binding.uri = data
        }

        override fun recycled() {
            if ((itemView.context as? Activity)?.isDestroyed == true) {
                return
            }
            Glide.with(itemView).clear(binding.ivImage)
        }
    }

}