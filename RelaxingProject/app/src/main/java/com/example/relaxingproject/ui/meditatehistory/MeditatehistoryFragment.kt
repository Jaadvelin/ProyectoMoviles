package com.example.relaxingproject.ui.meditatehistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.relaxingproject.R

class MeditatehistoryFragment : Fragment() {

    private lateinit var meditatehistoryViewModel : MeditatehistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        meditatehistoryViewModel =
            ViewModelProviders.of(this).get(MeditatehistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_meditatehistory, container, false)


        return root
    }
}