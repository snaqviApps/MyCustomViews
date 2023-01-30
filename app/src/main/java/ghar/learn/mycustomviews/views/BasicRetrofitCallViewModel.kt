package ghar.learn.mycustomviews.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.model.GithubPojo
import kotlinx.coroutines.launch

class BasicRetrofitCallViewModel(private val repository: BackEndRepository) : ViewModel() {

//    val repositoryLocal = BackEndRepository()                 // initalizing loally works too

    var uiDataProvider : LiveData<GithubPojo?>                  // works

    init {
        getBackEndData()
//        uiDataProvider = repositoryLocal.backEndDataProvider
        uiDataProvider = repository.backEndDataProvider
    }

    private fun getBackEndData() {
        viewModelScope.launch {
//            repositoryLocal.executeBackEndApi()
            repository.executeBackEndApi()
        }
    }

}
