package com.project.animalface_app.kmsapp.paging.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.animalface_app.R
import com.project.animalface_app.databinding.ItemViewBinding
import com.project.animalface_app.kmsapp.dto.UserItem

class MyViewHolderRetrofit(val binding: ItemViewBinding)
    : RecyclerView.ViewHolder(binding.root)

class MyAdapterRetrofit(val context: Context, val datas: List<UserItem>?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecyclerView.ViewHolder {
        return MyViewHolderRetrofit(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolderRetrofit).binding
        val item = datas?.get(position)
        binding.itemUsername.text = item?.memberId
        binding.itemName.text = item?.memberName


//        val imageUrl = "http://10.100.201.41:8080/api/users/${item?.memberNo}/profileImage"
        val imageUrl = "http://121.175.218.185:8081/api/users/${item?.memberNo}/profileImage"
//        val imageUrl = "http://192.168.219.200:8080/api/users/${item?.id}/profileImage"
        Glide.with(context)

            .load(imageUrl)  // profileImageId는 URL 또는 리소스 ID일 수 있습니다
            .override(300,300)
            .placeholder(R.drawable.test) // 이미지 로드 중에 표시할 플레이스홀더
            .error(R.drawable.user_basic) // 로드 실패 시 표시할 이미지
            .into(binding.itemProfileImage)
    }

}








