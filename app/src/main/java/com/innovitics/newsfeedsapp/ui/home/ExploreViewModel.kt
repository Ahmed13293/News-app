package com.innovitics.newsfeedsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innovitics.newsfeedsapp.data.network.Resource
import com.innovitics.newsfeedsapp.data.repository.ExploreRepo
import com.innovitics.newsfeedsapp.data.responses.everythingResponse
import com.innovitics.newsfeedsapp.data.responses.topHeadlinesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val exploreRepo: ExploreRepo): ViewModel(){

    var getEverythingNews: MutableLiveData<Resource<everythingResponse>> = MutableLiveData()
    var getTopHeadlinesArticles: MutableLiveData<Resource<topHeadlinesResponse>> = MutableLiveData()


    fun getEverythingNews(source: String) =
        viewModelScope.launch {
            getEverythingNews.postValue(exploreRepo.getEverythingNews(source))
        }

    fun getTopHeadlinesArticles(source: String) =
        viewModelScope.launch {
            getTopHeadlinesArticles.postValue(exploreRepo.getTopHeadlinesArticles(source))
        }

}