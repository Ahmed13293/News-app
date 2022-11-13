package com.innovitics.newsfeedsapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.innovitics.newsfeedsapp.R
import com.innovitics.newsfeedsapp.data.models.ArticlesListModel
import com.innovitics.newsfeedsapp.data.network.Resource
import com.innovitics.newsfeedsapp.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore), NewsAdapter.OnItemClickedInterface {

    private lateinit var binding: FragmentExploreBinding
    lateinit var viewModel: ExploreViewModel
    private lateinit var everythingNewsList: ArrayList<ArticlesListModel>
    private lateinit var topHeadlinesList: ArrayList<ArticlesListModel>
    private var list: ArrayList<ArticlesListModel> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentExploreBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity())[ExploreViewModel::class.java]


        loadData()
        everythingNewsObserver()

    }

    private fun setupPropertyTypeAdapter(list: ArrayList<ArticlesListModel>) {
        binding.newsRV.apply {
            val newsAdapter = NewsAdapter(list, this@ExploreFragment)
            binding.newsRV.adapter = newsAdapter
        }
    }

    private fun loadData() {
        viewModel.getEverythingNews("Apple")
        viewModel.getTopHeadlinesArticles("us")
    }

    private fun everythingNewsObserver() {
        viewModel.getEverythingNews.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    when (it.value.status) {
                        "ok" -> {
                            everythingNewsList = it.value.articles
                            list.addAll(everythingNewsList)
                            topHeadlinesObserver()

                        }
                        else -> {
                            Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                else -> {

                }
            }
        }
    }

    private fun topHeadlinesObserver() {
        viewModel.getTopHeadlinesArticles.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    when (it.value.status) {
                        "ok" -> {
                            topHeadlinesList = it.value.articles
                            list.addAll(topHeadlinesList)

                            setupPropertyTypeAdapter(list)
                        }
                        else -> {
                            Toast.makeText(requireContext(), "something went wrong", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                else -> {

                }
            }
        }
    }

    override fun onArticleClicked(listItem: ArticlesListModel) {
        val bundle = Bundle()
        bundle.putString("image", listItem.urlToImage)
        bundle.putString("title", listItem.title)
        bundle.putString("source", listItem.source.name)
        bundle.putString("content", listItem.content)

        view?.findNavController()
            ?.navigate(R.id.action_from_exploreFragment_to_newsDetailsFragment, bundle)
    }
}