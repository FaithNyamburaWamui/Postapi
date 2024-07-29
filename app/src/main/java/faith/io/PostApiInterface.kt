package faith.io

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiInterface {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @GET("/posts/{postId}")
    fun fetchPostById(@Path("postId") postId:Int):Call<Post>
}