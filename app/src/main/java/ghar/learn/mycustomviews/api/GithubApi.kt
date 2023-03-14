package ghar.learn.mycustomviews.api

import ghar.learn.mycustomviews.model.GithubUserProfile
import retrofit2.http.GET

const val GITHUB_END_POINT = "/users/snaqviapps"

interface GithubApi {
        @GET(GITHUB_END_POINT)
        suspend fun getGithubInfo() : GithubUserProfile?                        // ----> worked
//        suspend fun getGithubInfo() : ArrayList<GithubPojo?>?                 // ----> does not work
}