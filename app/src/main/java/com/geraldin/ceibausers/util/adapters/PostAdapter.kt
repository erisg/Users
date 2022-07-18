package com.geraldin.ceibausers.util.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geraldin.ceibausers.data.models.Post
import com.geraldin.ceibausers.databinding.PostItemBinding

class PostAdapter(
    private val posts: List<Post>
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return this.posts.size
    }

    inner class ViewHolder(binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val title: TextView = binding.titleTextView
        private val description: TextView = binding.descriptionTextView

        fun bind(item: Post) {
            title.text = item.title
            description.text = item.body
        }
    }
}
