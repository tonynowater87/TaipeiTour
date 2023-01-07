package com.tonynowater.cathaytest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.tonynowater.cathaytest.databinding.ActivityMainBinding
import com.tonynowater.cathaytest.feature.attraction_detail.AttractionDetailFragmentArgs
import com.tonynowater.cathaytest.feature.web.WebViewFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            binding.toolbar.subtitle = ""
            when (destination.id) {
                R.id.attractions -> {
                    binding.toolbar.title = getString(R.string.app_name)
                }
                R.id.attraction_detail -> {
                    binding.toolbar.title =
                        AttractionDetailFragmentArgs.fromBundle(arguments!!).title
                }
                R.id.web_view -> {
                    val fromBundle = WebViewFragmentArgs.fromBundle(arguments!!)
                    binding.toolbar.title = fromBundle.title
                    binding.toolbar.subtitle = fromBundle.webUrl
                }
                else -> {
                    binding.toolbar.title = getString(R.string.app_name)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}