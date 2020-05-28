package com.example.relaxingproject.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.relaxingproject.R

class LogFragment : Fragment() {

    private lateinit var logViewModel: LogViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        logViewModel =
                ViewModelProviders.of(this).get(LogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_log, container, false)
        return root
    }
}
