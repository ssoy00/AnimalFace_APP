package com.project.animalface_app.ksyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.animalface_app.R;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    private List<Notice> noticeList;

    public NoticeAdapter(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeViewHolder holder, int position) {
        Notice notice = noticeList.get(position);
        holder.noticeNo.setText(String.valueOf(notice.getNoticeNo()));
        holder.noticeName.setText(notice.getNoticeName());
        holder.noticeContents.setText(notice.getNoticeContents());
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public static class NoticeViewHolder extends RecyclerView.ViewHolder {
        public TextView noticeNo;
        public TextView noticeName;
        public TextView noticeContents;
        public TextView noticeDate;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            noticeNo = itemView.findViewById(R.id.noticeNo);
            noticeName = itemView.findViewById(R.id.noticeName);
            noticeContents = itemView.findViewById(R.id.noticeContents);
            noticeDate = itemView.findViewById(R.id.noticeDate);
        }
    }
}
