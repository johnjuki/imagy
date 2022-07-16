package com.example.imagy

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.imagy.adapter.EditorialFeedRecyclerViewAdapter
import com.example.imagy.databinding.FragmentMainBinding
import com.example.imagy.network.UnsplashApiService
import com.example.imagy.repository.UnsplashApiRepo
import kotlinx.coroutines.*

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: EditorialFeedRecyclerViewAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val service = UnsplashApiService.instance
        viewModel.unsplashApiRepo = UnsplashApiRepo(service)

        setUpRecyclerView()

        getUnsplashPhotos()

    }

    private fun setUpRecyclerView() {
        adapter = EditorialFeedRecyclerViewAdapter(this)
        binding.editorialPhotosRecyclerView.adapter = adapter
        adapter.submitList(null)
    }

    private fun getUnsplashPhotos() {
        showProgressBar()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(R.string.ok) { _, _ -> }
                .setIcon(R.drawable.ic_dialog_alert)
                .show()
        }

        lifecycleScope.launch(errorHandler) {
            val results = viewModel.editorialFeedPhotos()
            withContext(Dispatchers.Main) {
                hideProgressBar()
                adapter.submitList(results)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun showProgressBar() {
        binding.editorialFeedProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.editorialFeedProgressBar.visibility = View.INVISIBLE
    }

}
