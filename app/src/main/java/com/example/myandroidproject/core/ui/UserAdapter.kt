package com.example.myandroidproject.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myandroidproject.R
import com.example.myandroidproject.core.domain.model.Data
import com.example.myandroidproject.databinding.ItemListUserBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var listData = ArrayList<Data>()
    var onItemClick: ((Data) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Data>?) {
        if (data == null) return
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
       return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListUserBinding.bind(itemView)
        fun bind(data: Data) {
            with(binding) {
                Glide.with(ivIcon.context)
                    .load(data.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivIcon)
                tvName.text = data.login
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}