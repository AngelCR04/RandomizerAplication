package com.example.randomizeraplication

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randomizeraplication.databinding.DialogCoinBinding
import com.example.randomizeraplication.databinding.DialogDiceBinding

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val _diceResult = MutableLiveData<Int>()
    val diceResult: LiveData<Int>
        get() = _diceResult

    private val _coinResult = MutableLiveData<Boolean>()
    val coinResult: LiveData<Boolean>
        get() = _coinResult

    private val _showDiceResultDialog = MutableLiveData<Boolean>()
    val showDiceResultDialog: LiveData<Boolean>
        get() = _showDiceResultDialog

    private val _showCoinResultDialog = MutableLiveData<Boolean>()
    val showCoinResultDialog: LiveData<Boolean>
        get() = _showCoinResultDialog

    init {
        _diceResult.value = 1
        _coinResult.value = true
    }

    fun rollDice() {
        _diceResult.value = (1..6).random()
        _showDiceResultDialog.value = true
    }

    fun flipCoin() {
        _coinResult.value = (0..1).random() == 0
        _showCoinResultDialog.value = true
    }

    fun showDiceResultDialog(context: Context, numeroAleatorio: Int) {
        val binding: DialogDiceBinding = DialogDiceBinding.inflate(LayoutInflater.from(context))

        binding.imageViewDiceDialog.setImageResource(
            context.resources.getIdentifier("dado$numeroAleatorio", "drawable", context.packageName)
        )

        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Dice result:")
            .setMessage("Number obtained: $numeroAleatorio")
            .setView(binding.root)
            .setPositiveButton("OK", null)
            .create()

        alertDialog.show()
    }

    fun showCoinResultDialog(context: Context, isHeads: Boolean) {
        val binding: DialogCoinBinding = DialogCoinBinding.inflate(LayoutInflater.from(context))

        binding.imageViewCoinDialog.setImageResource(
            context.resources.getIdentifier(if (isHeads) "face" else "cross", "drawable", context.packageName)
        )

        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Coin result:")
            .setMessage("Side obtained: ${if (isHeads) "Face" else "Cross"}")
            .setView(binding.root)
            .setPositiveButton("OK", null)
            .create()

        alertDialog.show()
    }

    fun resetShowDiceResultDialog() {
        _showDiceResultDialog.value = false
    }

    fun resetShowCoinResultDialog() {
        _showCoinResultDialog.value = false
    }
}