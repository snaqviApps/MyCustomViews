package ghar.learn.mycustomviews.api

import GithubUserData
import retrofit2.http.GET

const val GITHUB_END_POINT = "/users/snaqviapps"

interface GithubApi {
        @GET(GITHUB_END_POINT)
//        suspend fun getGithubInfo() : List<GithubUserProfile?>?
        suspend fun getGithubInfo() : List<GithubUserData?>?
}