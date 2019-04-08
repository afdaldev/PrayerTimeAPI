package com.example.adzan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adzan.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.adzan.data.model.AdzanResponse.*;

public class PrayerTimesAdapter extends RecyclerView.Adapter<PrayerTimesAdapter.ViewHolder> {

    private List<DataBean> dataBeanList;

    public PrayerTimesAdapter(List<DataBean> dataBeanList){
        this.dataBeanList = dataBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_prayer_by_city, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mDay.setText(dataBeanList.get(position).getDate().getGregorian().getWeekday().getEn());
        holder.mDate.setText(dataBeanList.get(position).getDate().getReadable());

        holder.mFajrTime.setText(dataBeanList.get(position).getTimings().getFajr());
        holder.mDhuhrTime.setText(dataBeanList.get(position).getTimings().getDhuhr());
        holder.mAsrTime.setText(dataBeanList.get(position).getTimings().getAsr());
        holder.mMaghribTime.setText(dataBeanList.get(position).getTimings().getMaghrib());
        holder.mIshaTime.setText(dataBeanList.get(position).getTimings().getIsha());
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mDay, mDate;
        private TextView mFajrTime, mDhuhrTime, mAsrTime, mMaghribTime, mIshaTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mDay = itemView.findViewById(R.id.tv_day);
            mDate = itemView.findViewById(R.id.tv_current_date);

            mFajrTime = itemView.findViewById(R.id.tv_fajr_time);
            mDhuhrTime = itemView.findViewById(R.id.tv_dhuhr_time);
            mAsrTime = itemView.findViewById(R.id.tv_asr_time);
            mMaghribTime = itemView.findViewById(R.id.tv_maghrib_time);
            mIshaTime = itemView.findViewById(R.id.tv_isha_time);
        }
    }
}
