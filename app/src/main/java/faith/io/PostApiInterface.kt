package faith.io

import retrofit2.Call
import retrofit2.http.GET

interface PostApiInterface {
    @GET("post")
    fun getPost(): Call<List<Post>>
}