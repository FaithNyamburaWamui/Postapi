package faith.io.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import faith.io.model.Comments
import faith.io.databinding.ActivityCommentsBinding

class AdapterComments(var comments:List<Comments>):RecyclerView.Adapter<CommentViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding=ActivityCommentsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val list=comments[position]
        holder.binding.tvPostBody.text=list.body
        holder.binding.tvPostName.text=list.name
    }
}

class CommentViewHolder(var binding: ActivityCommentsBinding):RecyclerView.ViewHolder(binding.root){

}