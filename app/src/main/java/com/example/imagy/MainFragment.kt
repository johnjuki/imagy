package com.example.imagy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class MainFragment : Fragment() {

    private val viewModel : MainViewModel by viewModels()
    private lateinit var statusTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statusTextView = view.findViewById(R.id.statusTextView)

        // Create an observer which updates the UI
        val photosStatusObserver = Observer<String> {
            statusTextView.text = it
        }

        // Observe the LiveData
        viewModel.status.observe(viewLifecycleOwner, photosStatusObserver)
    }

}
