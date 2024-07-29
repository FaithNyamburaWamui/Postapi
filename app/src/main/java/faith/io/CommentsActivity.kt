package faith.io

import Apiclient
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import faith.io.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentsBinding
    var postId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.extras != null){
            postId=intent.getIntExtra("POST_ID",0)
            if(postId!=0){
                fetchPost()
            }
            else{
                Toast.makeText(this,"Post Id not found", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun fetchPost(){
        val apiClient=Apiclient.buildApiClient(PostApiInterface::class.java)
        val request=apiClient.fetchPostById(postId)
        request.enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    binding.tvPostTittle.text = post?.tittle
                    binding.tvPostBody.text = post?.body
                }
                else{
                    Toast.makeText(this@CommentsActivity,response.errorBody()?.string(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(p0: Call<Post>, t: Throwable) {
                Toast.makeText(this@CommentsActivity,t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}