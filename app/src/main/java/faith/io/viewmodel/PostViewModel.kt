package faith.io.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import faith.io.model.Comments
import faith.io.model.Post
import faith.io.model.PostRequest
import faith.io.repository.PostsRepository
import kotlinx.coroutines.launch

class PostViewModel:ViewModel() {
    val postsRepo=PostsRepository()
    val postsLiveData= MutableLiveData<List<Post>>()
    val errorLiveData=MutableLiveData<String>()
    val postLiveData= MutableLiveData<Post>()
    val commentsLiveData= MutableLiveData<List<Comments>>()
    val createPostLiveData=MutableLiveData<String>()

    fun fetchPost(){
        viewModelScope.launch {
            val response = postsRepo.fetchPosts()
            if(response.isSuccessful){
                postsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

//    suspend fun fetchPostById(postId:Int){
//            viewModelScope.launch {
//                val response=postsRepo.fetchPostById(postId)
//                if (response.isSuccessful){
//                    postLiveData.postValue(response.body())
//                }
//                else{
//                    errorLiveData.postValue(response.errorBody()?.string())
//                }
//            }
//        }
      fun fetchComments(postId: Int){
        viewModelScope.launch {
            val response=postsRepo.fetchComments(postId)
            if (response.isSuccessful){
                commentsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun createPost(postRequest: PostRequest){
        viewModelScope.launch {
            var response=postsRepo.createPost(postRequest)
            if(response.isSuccessful){
              createPostLiveData.postValue("Post created successfully")
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    }
