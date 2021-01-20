package com.aim.leantechtest.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aim.leantechtest.R
import com.aim.leantechtest.data.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class UserListAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<UserListAdapter.DataViewHolder>() {

    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    fun setOnItemClickListener (listener: OnItemClickListener) {
        this.listener = listener
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User, listener: OnItemClickListener?, users: ArrayList<User>) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.id.toString()
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)

            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(users[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position], listener, users)

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}