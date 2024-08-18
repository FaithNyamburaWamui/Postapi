package faith.io.api

import faith.io.model.Comments
import faith.io.model.Post
import faith.io.model.PostRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApiInterface {
    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    suspend fun fetchPostById(@Path("postId") postId: Int): Response<Post>

    @GET("/posts/{postId}/comments")
    suspend fun fetchComments(@Path("postId") postId: Int): Response<List<Comments>>


    @POST("/posts")
    suspend fun createPost(@Body postRequest: PostRequest): Response<Post>

}
