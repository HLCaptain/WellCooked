package hu.wellcooked

import android.os.Bundle
import android.util.Log
import hu.wellcooked.databinding.ActivityMainBinding
import hu.wellcooked.fragment.courier.CourierFragment
import hu.wellcooked.fragment.customer.CustomerFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        binding.topAppBar.setNavigationOnClickListener {
            if (!binding.navigationDrawer.isOpen) binding.navigationDrawer.open()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, CustomerFragment.getInstance())
            .commit()
        binding.navigationView.setCheckedItem(binding.navigationView.menu.findItem(R.id.customerItem))

        // don't remove this
        binding.navigationView.bringToFront()
        binding.navigationView.setNavigationItemSelectedListener {
            Log.d("NAV_DRAWER", "Item selected")
            return@setNavigationItemSelectedListener when (it.itemId) {
                R.id.courierItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, CourierFragment.getInstance())
                        .commit()
                    binding.navigationDrawer.close()
                    true
                }
                R.id.customerItem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, CustomerFragment.getInstance())
                        .commit()
                    binding.navigationDrawer.close()
                    true
                }
                else -> false
            }
        }
    }
}