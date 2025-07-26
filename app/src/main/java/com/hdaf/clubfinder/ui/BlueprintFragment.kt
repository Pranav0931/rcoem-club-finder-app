package com.hdaf.clubfinder.ui // Corrected package name

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hdaf.clubfinder.R // Corrected import

class BlueprintFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // This inflates the updated layout file
        return inflater.inflate(R.layout.fragment_blueprint, container, false)
    }
}
