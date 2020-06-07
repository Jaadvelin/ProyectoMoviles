package com.example.relaxingproject.ui.meditate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.relaxingproject.R

class MeditateFragment : Fragment() {

    private lateinit var meditateViewModel: MeditateViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        meditateViewModel =
                ViewModelProviders.of(this).get(MeditateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_meditate, container, false)
        return root
    }
}
