package com.example.randomizeraplication


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.randomizeraplication.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.showDiceResultDialog.observe(this) { show ->
            if (show) {
                viewModel.resetShowDiceResultDialog()
                viewModel.showDiceResultDialog(this, viewModel.diceResult.value ?: 1)
            }
        }

        viewModel.showCoinResultDialog.observe(this) { show ->
            if (show) {
                viewModel.resetShowCoinResultDialog()
                viewModel.showCoinResultDialog(this, viewModel.coinResult.value ?: true)
            }
        }
    }
}