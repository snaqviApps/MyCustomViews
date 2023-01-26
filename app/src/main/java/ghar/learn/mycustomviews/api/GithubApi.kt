package ghar.learn.mycustomviews.api

import GithubUserProfile
import retrofit2.http.GET

const val GITHUB_END_POINT = "/users/snaqviapps"

interface GithubApi {
        @GET(GITHUB_END_POINT)
        suspend fun getGithubInfo() : List<GithubUserProfile?>?
}