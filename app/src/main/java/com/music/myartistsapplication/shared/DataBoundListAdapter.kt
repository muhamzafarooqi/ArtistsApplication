package com.music.myartistsapplication.shared

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias OnItemClickListener = (itemPosition: Int) -> Unit

/**
 * A generic RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The type of the ViewDataBinding
 */
abstract class DataBoundListAdapter<T, VB : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) : ListAdapter<T, DataBoundViewHolder<VB>>(diffCallback) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<VB> =
        DataBoundViewHolder(createBinding(parent)).also { viewHolder ->
            onItemClickListener?.let {
                viewHolder.binding.root.setOnClickListener {
                    onItemClickListener?.invoke(viewHolder.adapterPosition)
                }
            }
        }

    protected abstract fun createBinding(parent: ViewGroup): VB

    override fun onBindViewHolder(holder: DataBoundViewHolder<VB>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun bind(binding: VB, item: T)
}

/**
 * A generic ViewHolder that works with a [ViewDataBinding].
 * @param <T> The type of the ViewDataBinding.
 */
class DataBoundViewHolder<out T : ViewDataBinding> constructor(val binding: T) : RecyclerView.ViewHolder(binding.root)
