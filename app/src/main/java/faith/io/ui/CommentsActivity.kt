package faith.io.ui

import faith.io.api.Apiclient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import faith.io.model.Comments
import faith.io.model.Post
import faith.io.api.PostApiInterface
import faith.io.databinding.ActivityCommentsBinding
import faith.io.viewmodel.PostViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    private var postId = 0
    private lateinit var binding: ActivityCommentsBinding
    val postViewModel: PostViewModel by viewModels()
    lateinit var comments: AdapterComments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null){
            postId = extra.getInt("POST_ID")
            postViewModel.fetchPost()
            postViewModel.fetchComments(postId)
        }

        binding.rvComments.layoutManager=LinearLayoutManager(this)

    }


    override fun onResume(){
        super.onResume()
        postViewModel.postLiveData.observe(this){post->
            binding.tvPostName.text = post?.tittle
            binding.tvPostBody.text = post?.body

        }

        postViewModel.errorLiveData.observe(this) { error ->
            Toast.makeText(this@CommentsActivity, error, Toast.LENGTH_LONG).show()
        }

        postViewModel.commentsLiveData.observe(this) { comments ->
            displayComments(comments)
        }

    }

   fun displayComments(comments: List<Comments>){
       binding.rvComments.adapter=AdapterComments(comments)
   }

}