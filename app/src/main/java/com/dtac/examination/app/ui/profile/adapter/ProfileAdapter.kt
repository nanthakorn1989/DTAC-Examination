package com.dtac.examination.app.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dtac.examination.app.R
import com.dtac.examination.app.databinding.ProfileItemViewBinding
import com.dtac.examination.app.ui.profile.model.ProfileResponseModel

class ProfileAdapter() : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    var data: List<ProfileResponseModel.ProfileInfo>? = null
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        var binding: ProfileItemViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.profile_item_view, parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ProfileViewHolder(private val binding: ProfileItemViewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(profile: ProfileResponseModel.ProfileInfo){
            binding.apply {
                Glide.with(binding.root.context).load(profile.pic.medium).into(ivProfile)
                tvName.text = "${profile.name.title} ${profile.name.firstName} ${profile.name.lastName}"
                tvAge.text = "${profile.dob.age}"
                tvEmail.text = profile.email
            }
        }
    }
}