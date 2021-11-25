package hu.wellcooked

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import hu.wellcooked.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navControllerDelegate: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // don't remove this
        binding.navDrawer.bringToFront()
    }

    override fun onStart() {
        super.onStart()

        binding.navDrawer.setupWithNavController(
            binding.drawerNavHostFragment.findNavController()
        )
    }

    override fun onNavigateUp(): Boolean {
        if (binding.navDrawerLayout.isOpen) {
            binding.navDrawerLayout.close()
        } else {
            binding.navDrawerLayout.open()
        }
        return super.onNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (navControllerDelegate.graph.startDestinationId == navControllerDelegate.currentDestination?.id) {
            if (binding.navDrawerLayout.isOpen) {
                binding.navDrawerLayout.close()
            } else {
                binding.navDrawerLayout.open()
            }
            false
        } else {
            navControllerDelegate.navigateUp()
            true
        }
    }

    fun setNavController(navController: NavController) {
        navControllerDelegate = navController
        setupActionBarWithNavController(
            navControllerDelegate,
            AppBarConfiguration(
                navControllerDelegate.graph,
                binding.navDrawerLayout
            )
        )
    }
}
