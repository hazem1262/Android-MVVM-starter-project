package com.example.mvvmstarterproject.ui.main.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.data.users.remote.User
import com.example.mvvmstarterproject.databinding.UserRowItemBinding

class UsersAdapter(val selectUser:(User)->Unit): ListAdapter<User, RecyclerView.ViewHolder>(diffCallBack)  {
    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean = newItem == oldItem

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean = oldItem.id == newItem.id
        }

        @JvmStatic
        @BindingAdapter("users")
        fun RecyclerView.bindItems(items: MutableLiveData<List<User>>?) {
            val adapter = adapter as UsersAdapter
            items?.observeForever {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_row_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(currentList[position])
    }

    inner class UserViewHolder(private var binding: UserRowItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(item:User){
            binding.apply {
                root.setOnClickListener {
                    selectUser(item)
                }
                user = item
                executePendingBindings()
            }
        }
    }
}

