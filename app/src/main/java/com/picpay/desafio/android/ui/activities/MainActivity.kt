package com.picpay.desafio.android.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.picpay.desafio.android.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!navController.navigateUp()) {
            onBackPressed()
        }
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun init() {
        setSupportActionBar(toolbar_graph)
        setupActionBarWithNavController(navController)
    }
}
