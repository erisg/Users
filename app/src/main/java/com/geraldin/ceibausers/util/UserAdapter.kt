package com.geraldin.ceibausers.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geraldin.ceibausers.data.models.User
import com.geraldin.ceibausers.databinding.UserItemBinding

class UserAdapter(
    private val services: List<User>,
    private val userSelect: IUserSelect
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(services[position])
    }

    override fun getItemCount(): Int {
        return this.services.size
    }

    inner class ViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name: TextView = binding.userNameTextView
        private val email: TextView = binding.emailTextView
        private val phone: TextView = binding.phoneTextView
        private val viewPublication: TextView = binding.viewPublicationTextView

        fun bind(item: User) {
            name.text = item.name
            phone.text = item.phone
            email.text = item.email

            viewPublication.setOnClickListener {
                userSelect.selectedUser(item.id)
            }
        }
    }
}
