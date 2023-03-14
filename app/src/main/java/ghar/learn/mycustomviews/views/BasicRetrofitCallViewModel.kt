package ghar.learn.mycustomviews.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.model.GithubUserProfile
import kotlinx.coroutines.launch

class BasicRetrofitCallViewModel(private val repository: BackEndRepository) : ViewModel() {

    var uiDataProvider : LiveData<GithubUserProfile?>
    init {
        getBackEndData()
        uiDataProvider = repository.backEndDataProvider
    }

    private fun getBackEndData() {
        viewModelScope.launch {
            repository.executeBackEndApi()
        }
    }

}
