package com.picpay.desafio.android.presenter

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.local.UserDao
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presenter.adapter.UserListAdapter
import org.koin.android.ext.android.bind
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity() : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: PicPayViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.userListProgressBar.visibility = View.VISIBLE

        viewModel.users.observe(this, Observer { listUserUiModel ->
            if (listUserUiModel.isNullOrEmpty()) {
                val message = getString(R.string.error)
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            } else {
                binding.userListProgressBar.visibility = View.GONE
                binding.recyclerView.adapter = UserListAdapter(listUserUiModel)
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
            }
        })

        viewModel.getUsers()
    }
}
