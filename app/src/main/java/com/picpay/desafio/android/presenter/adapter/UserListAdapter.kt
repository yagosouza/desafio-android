package com.picpay.desafio.android.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presenter.model.UserUiModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter(
    private val users: List<UserUiModel>
) : RecyclerView.Adapter<UserListAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = users[position]

        with(holder) {
            binding.name.text = user.name
            binding.username.text = user.username
            binding.progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(binding.picture, object : Callback {
                    override fun onSuccess() {
                        binding.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        binding.progressBar.visibility = View.GONE
                    }
                })
        }
    }

    override fun getItemCount(): Int = users.size

    inner class UsersViewHolder(val binding: ListItemUserBinding) :
            RecyclerView.ViewHolder(binding.root)
}