package hu.wellcooked.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.preference.PreferenceFragmentCompat
import hu.wellcooked.R
import hu.wellcooked.databinding.FragmentSettingsBinding

class SettingsFragment : PreferenceFragmentCompat() {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //setPreferencesFromResource(R.xml.root_preferences, rootKey)
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", requireActivity().packageName, null)
        startActivity(intent)
    }
}