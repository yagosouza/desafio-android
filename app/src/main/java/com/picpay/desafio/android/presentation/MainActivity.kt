package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: PicPayViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        handleObserver()
    }

    private fun handleObserver() {
        viewModel.users.observe(this) { listUserUiModel ->
            binding.recyclerView.adapter = UserListAdapter(listUserUiModel)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }

        viewModel.isLoading.observe(this) {
            binding.userListProgressBar.isVisible = it
        }

        viewModel.showGenericError.observe(this) {
            showToast(getString(R.string.error))
        }

        viewModel.showNoCachedUsersError.observe(this) {
            showToast(getString(R.string.errorNoCachedUsers))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
