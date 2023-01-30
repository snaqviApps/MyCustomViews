package ghar.learn.mycustomviews.views

import GithubUserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghar.learn.mycustomviews.api.BackEndRepository
import kotlinx.coroutines.launch

class BasicRetrofitCallViewModel(private val repository: BackEndRepository) : ViewModel() {
//class BasicRetrofitCallViewModel() : ViewModel() {

    private val repositoryLocal = BackEndRepository()
    var uiDataProvider : LiveData<List<GithubUserData?>?>

    init {
        getBackEndData()
        uiDataProvider = repositoryLocal.backEndDataProvider
    }

    private fun getBackEndData() {
        viewModelScope.launch {
            repositoryLocal.executeBackEndApi()
        }
    }

}
