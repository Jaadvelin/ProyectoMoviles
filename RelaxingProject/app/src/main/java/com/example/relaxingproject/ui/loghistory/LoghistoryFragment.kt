package com.example.relaxingproject.ui.loghistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.relaxingproject.R

class LoghistoryFragment : Fragment() {

    private lateinit var loghistoryViewModel : LoghistoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        loghistoryViewModel =
                ViewModelProviders.of(this).get(LoghistoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_loghistory, container, false)
        return root
    }
}