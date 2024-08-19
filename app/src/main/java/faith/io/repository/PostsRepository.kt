package faith.io.repository

import faith.io.api.Apiclient
import faith.io.api.PostApiInterface
import faith.io.model.Comments
import faith.io.model.Post
import faith.io.model.PostRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostsRepository {
    val apiClient=Apiclient.buildApiClient(PostApiInterface::class.java)

    suspend fun fetchPosts(): Response<List<Post>>{
        return withContext(Dispatchers.IO){
            apiClient.getPosts()
        }
    }

    suspend fun fetchComments(postId: Int):Response<List<Comments>>{
        return  withContext(Dispatchers.IO){
            apiClient.fetchComments(postId)
        }
    }

    suspend fun createPost(postRequest:PostRequest):Response<Post>{
        return withContext(Dispatchers.IO){
            apiClient.createPost(postRequest)
        }
    }
}