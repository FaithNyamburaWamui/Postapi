package faith.io

import Apiclient
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import faith.io.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchPosts()
        binding.rvRecycler.layoutManager=LinearLayoutManager(this)

    }

    fun fetchPosts(){
        val apiInterface=Apiclient.buildApiInterface(PostApiInterface::class.java)
        val request = apiInterface.getPosts()
        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
                if(p1.isSuccessful){
                    val posts = p1.body()
                    posts?.let {
                        val adapterPost=AdapterPost(it)
                        binding.rvRecycler.adapter=adapterPost
                    }
                    Toast.makeText(baseContext,"Fetch ${posts!!.size} posts", Toast.LENGTH_LONG).show()
                }
                else if(p1 !=null){
                    Toast.makeText(baseContext,"No post recieved",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
               Toast.makeText(baseContext,p1.message.toString(),Toast.LENGTH_LONG).show()
            }

        })
    }
}