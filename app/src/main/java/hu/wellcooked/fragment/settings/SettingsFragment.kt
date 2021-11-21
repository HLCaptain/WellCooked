package hu.wellcooked.fragment.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import hu.wellcooked.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}