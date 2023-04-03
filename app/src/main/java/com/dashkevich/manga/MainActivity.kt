package com.dashkevich.manga

import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configSystemUI()

        navController =
            (supportFragmentManager.findFragmentById(R.id.root_container) as NavHostFragment).navController
        navController.setGraph(com.dashkevich.navigation.R.navigation.root_nav_graph)
    }


    private fun configSystemUI() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val wic = WindowInsetsControllerCompat(window, window.decorView)

        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                wic.isAppearanceLightStatusBars = true
                wic.isAppearanceLightNavigationBars = true
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                //If don't have night theme
                wic.isAppearanceLightStatusBars = true
                wic.isAppearanceLightNavigationBars = true
            }
        }
    }

}