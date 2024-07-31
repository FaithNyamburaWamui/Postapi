package faith.io

import retrofit2.http.Body

data class Comments(
    val postId:Int,
    val id:Int,
    val name:String,
    val email:String,
    val body: String
)
