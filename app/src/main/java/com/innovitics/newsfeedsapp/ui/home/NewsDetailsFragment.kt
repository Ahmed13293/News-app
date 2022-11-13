package com.innovitics.newsfeedsapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.innovitics.newsfeedsapp.R
import com.innovitics.newsfeedsapp.databinding.NewsDetailsLayoutBinding

class NewsDetailsFragment : Fragment(R.layout.news_details_layout) {

    private lateinit var binding: NewsDetailsLayoutBinding
    private var title: String? = null
    private var image: String? = null
    private var source: String? = null
    private var content: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString("title")?.let {
            title = it
        }
        arguments?.getString("image")?.let {
            image = it
        }
        arguments?.getString("source")?.let {
            source = it
        }
        arguments?.getString("content")?.let {
            content = it
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsDetailsLayoutBinding.bind(view)

        binding.newsTitle.text = title
        binding.newsDesc.text = source
        binding.newsContent.text = content
        Glide.with(requireContext()).load(image)
            .into(binding.newsImg)
    }
}