package ghar.learn.mycustomviews.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ghar.learn.mycustomviews.model.GithubPojo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val MAX_TIME_OUT: Long = 1400L

class BackEndRepository {

    private var _backEndDataProvider =  MutableLiveData<GithubPojo?>()                  //   ---------> worked
//    private var _backEndDataProvider =  MutableLiveData<ArrayList<GithubPojo?>?>()    // does not work
    val backEndDataProvider : LiveData<GithubPojo?> = _backEndDataProvider
    private val TAG: String? = this.javaClass.canonicalName

    private var githubApi: GithubApi
    private val baseUrl = "https://api.github.com"

    private fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init {
        println("repo instantiated")
        githubApi = provideRetrofit().create(GithubApi::class.java)
    }

    suspend fun executeBackEndApi(){
        withContext(Dispatchers.IO) {
            withTimeout(MAX_TIME_OUT) {
                try {
                    val rawGithubData = githubApi.getGithubInfo()
                    rawGithubData?.let {
                        Log.d(TAG, "github-data: $rawGithubData")
                        _backEndDataProvider.postValue(rawGithubData)

                    } ?:run {
                        _backEndDataProvider.postValue(null)
                    }
                } catch (ex : Exception){
                    Log.e(TAG, "error message: ${ex.message}")
                }
            }
        }
    }
}
