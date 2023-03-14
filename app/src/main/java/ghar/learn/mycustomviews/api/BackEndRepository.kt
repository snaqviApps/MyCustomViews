package ghar.learn.mycustomviews.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ghar.learn.mycustomviews.model.GithubUserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass

const val MAX_TIME_OUT: Long = 1400L

class BackEndRepository {

    private val _tag  = BackEndRepository::javaClass.toString()

    private lateinit var formattedOutput : String

    //    private var _backEndDataProvider =  MutableLiveData<ArrayList<GithubPojo?>?>()           // does not work
    private var _backEndDataProvider =  MutableLiveData<GithubUserProfile?>()                  //   ---------> worked
    val backEndDataProvider : LiveData<GithubUserProfile?> = _backEndDataProvider

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
//                        val jsonArray = buildJsonArray { githubUserProfile }
                        val builtJsonObject = buildJsonObject {
                            formattedOutput  = it.avatarUrl.toString()
                        }
                        Log.d(_tag, "github-data: ${it}, '\n', avaterUrl: $formattedOutput")
                        _backEndDataProvider.postValue(rawGithubData)

                    } ?:run {
                        _backEndDataProvider.postValue(null)
                    }
                } catch (ex : Exception){
                    Log.e(_tag, "error message: ${ex.message}")
                }
            }
        }
    }
}
