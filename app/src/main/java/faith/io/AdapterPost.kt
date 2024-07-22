package faith.io

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import faith.io.databinding.PostListItemBinding

class AdapterPost (val body:List<Post>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding=PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return body.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val body=body[position]
        holder.binding.tittle.text=body.tittle
        holder.binding.id.text=body.id.toString()
        holder.binding.user.text=body.userId.toString()
        holder.binding.body.text=body.body

    }
}

class PostViewHolder(var binding: PostListItemBinding):RecyclerView.ViewHolder(binding.root){

}