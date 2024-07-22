package faith.io

import retrofit2.Call
import retrofit2.http.GET

interface PostApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>
}