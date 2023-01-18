package ghar.learn.mycustomviews.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ghar.learn.mycustomviews.api.BackEndRepository

class BasicRetrofitCallViewModelFactory constructor(private val repository: BackEndRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BasicRetrofitCallViewModel::class.java)) {
            BasicRetrofitCallViewModel(this.repository) as T
//            BasicRetrofitCallViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}