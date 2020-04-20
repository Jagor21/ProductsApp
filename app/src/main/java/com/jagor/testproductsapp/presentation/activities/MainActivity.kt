package com.jagor.testproductsapp.presentation.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jagor.testproductsapp.R

class MainActivity : FragmentActivity() {

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )
        bindViewModel()
    }

    private fun bindViewModel() {

    }
}
