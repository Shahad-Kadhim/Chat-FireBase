package com.shahad.app.chat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.shahad.app.chat.BR
import com.shahad.app.chat.R
import com.shahad.app.chat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MessageInteractionListener {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).also { binding ->
            binding.lifecycleOwner=this
            binding.setVariable(BR.viewModel, viewModel)
        }
        setupRecycle()
    }

    private fun setupRecycle() {
        binding.recyclerView.adapter =
            MessageRecycleAdapter(
                 emptyList(),
                this
            )
    }
}