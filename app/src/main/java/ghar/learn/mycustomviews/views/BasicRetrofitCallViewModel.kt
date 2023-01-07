package ghar.learn.mycustomviews.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ghar.learn.mycustomviews.backend.BackEndRepository

class BasicRetrofitCallViewModel(repository: BackEndRepository) : ViewModel() {

    private val _repoTitle = MutableLiveData<String>()
    val repoTitle: LiveData<String> = _repoTitle

}
