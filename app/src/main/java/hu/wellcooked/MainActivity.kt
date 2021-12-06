package hu.wellcooked

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hu.wellcooked.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
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
//        binding.navDrawer.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.settingsFragment -> {
//
//                    true
//                }
//                else -> true
//            }
//        }

        if (Firebase.auth.currentUser != null) {
            intent.dataString?.let {
                val recipeId = it.replace("https://wellcooked.com/recipeId/", "").toInt()
                // testing URI = https://wellcooked.com/recipeId/7834
                binding.drawerNavHostFragment.findNavController().navigate(R.id.nav_graph_recipe_info, bundleOf(Pair("recipeId", recipeId)))
            }
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
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
        return if (navControllerDelegate.graph.startDestination == navControllerDelegate.currentDestination?.id) {
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
