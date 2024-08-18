package faith.io.ui

import faith.io.api.Apiclient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import faith.io.model.Post
import faith.io.api.PostApiInterface
import faith.io.databinding.ActivityMainBinding
import faith.io.viewmodel.PostViewModel
import kotlinx.coroutines.flow.combine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val postViewModel:PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postViewModel.fetchPost()
        binding.rvRecycler.layoutManager=LinearLayoutManager(this)

    }

    override fun onResume(){
        super.onResume()
        postViewModel.postsLiveData.observe(this){posts->
            displayPosts(posts)
        }

        postViewModel.errorLiveData.observe(this){error->
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        }
    }
    fun displayPosts(posts:List<Post>){
        binding.rvRecycler.adapter=AdapterPost(posts,this)
    }
}

