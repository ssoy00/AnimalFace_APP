package com.project.animalface_app.ohjapp.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.project.animalface_app.R
import com.project.animalface_app.ohjapp.ReadActivity
import com.project.animalface_app.ohjapp.models.Notice

class NoticeAdapter(private val context: Context, private val noticeList: List<Notice>) :
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notice_item, parent, false)
        return NoticeViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = noticeList[position]
        holder.numberTextView.text = notice.number.toString()
        holder.titleTextView.text = notice.title
        holder.dateTextView.text = notice.date

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ReadActivity::class.java)
            intent.putExtra("notice", notice)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val numberTextView: TextView = itemView.findViewById(R.id.noticeNumber)
        val titleTextView: TextView = itemView.findViewById(R.id.noticeTitle)
        val dateTextView: TextView = itemView.findViewById(R.id.noticeDate)
    }
}