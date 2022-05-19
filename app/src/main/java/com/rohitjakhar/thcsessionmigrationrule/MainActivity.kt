package com.rohitjakhar.thcsessionmigrationrule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.rohitjakhar.thcsessionmigrationrule.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dao by lazy {
        AppDatabase.getInstance(this@MainActivity).myDap()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
    }

    private fun onClick() = binding.apply {
        btnSubmitUser.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                withContext(Dispatchers.IO) {
                    dao.addName(NameEntity(name = inputName.editText?.text.toString()))
                    dao.addNumber(
                        NumberEntity(
                            number = inputNumber.editText?.text.toString().toLong()
                        )
                    )
                }
            }
        }
    }
}
