package faith.io

import Apiclient
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    fun fetchPosts(){
        val apiInterface=Apiclient.buildApiInterface(PostApiInterface::class.java)
        val request = apiInterface.getPost()
        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(p0: Call<List<Post>>, p1: Response<List<Post>>) {
                if(p1.isSuccessful){
                    var posts = p1.body()
                    Toast.makeText(baseContext,"Fetch ${posts!!.size} posts", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<List<Post>>, p1: Throwable) {
               Toast.makeText(baseContext,p1.message.toString(),Toast.LENGTH_LONG)
            }

        })
    }
}