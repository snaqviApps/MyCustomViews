package ghar.learn.mycustomviews.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.model.GitHubBasicInfoModel
import kotlinx.coroutines.launch

class BasicRetrofitCallViewModel(private val repository: BackEndRepository) : ViewModel() {
//class BasicRetrofitCallViewModel() : ViewModel() {

    private val repositoryLocal = BackEndRepository()
    var uiDataProvider : LiveData<Array<GitHubBasicInfoModel>?>

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
