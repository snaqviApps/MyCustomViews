package ghar.learn.mycustomviews.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ghar.learn.mycustomviews.api.BackEndRepository
import ghar.learn.mycustomviews.model.GithubUserProfile
import kotlinx.coroutines.launch

class BasicRetrofitCallViewModel(private val repository: BackEndRepository) : ViewModel() {

//    val repositoryLocal = BackEndRepository()                 // initalizing loally works too
    var uiDataProvider : LiveData<GithubUserProfile?>

    init {
        getBackEndData()
//        uiDataProvider = repositoryLocal.backEndDataProvider  // using local-variable
        uiDataProvider = repository.backEndDataProvider
    }

    private fun getBackEndData() {
        viewModelScope.launch {
//            repositoryLocal.executeBackEndApi()               // using local-variable
            repository.executeBackEndApi()
        }
    }

}
