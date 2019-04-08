package com.example.adzan.data.activity;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.adzan.R;
import com.example.adzan.data.model.AdzanResponse;
import com.example.adzan.data.model.AdzanService;
import com.example.adzan.data.model.CurrentDateResponse;
import com.example.adzan.data.model.CurrentTimeResponse;

import java.util.List;

import static com.example.adzan.data.model.AdzanResponse.*;

public class PrayerTime extends AppCompatActivity {

    public static String CURRENT_DATE = "CurrentDate";
    private TextView mDay, mCurrentDate, mCurrentTime;
    private TextView mFajrTime, mDhuhrTime, mAsrTime, mMaghribTime, mIshaTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_time);

        mDay = findViewById(R.id.tv_day);
        mCurrentDate = findViewById(R.id.tv_current_date);

        mFajrTime = findViewById(R.id.tv_fajr_time);
        mDhuhrTime = findViewById(R.id.tv_dhuhr_time);
        mAsrTime = findViewById(R.id.tv_asr_time);
        mMaghribTime = findViewById(R.id.tv_maghrib_time);
        mIshaTime = findViewById(R.id.tv_isha_time);

        mCurrentTime = findViewById(R.id.tv_current_time);

        AdzanService.getAPI().getPrayerTimeByCity("Makassar","ID").enqueue(new Callback<AdzanResponse>() {
            @Override
            public void onResponse(Call<AdzanResponse> call, Response<AdzanResponse> response) {
                if (response.isSuccessful()){
                    List<DataBean> dataBeans = response.body().getData();

                    for (AdzanResponse.DataBean dataBean: dataBeans){
                        AdzanResponse.DataBean.DateBean dateBean = dataBean.getDate();
                        AdzanResponse.DataBean.TimingsBean timingsBean = dataBean.getTimings();
                        Log.d("Jadwal Shalat Ashar", timingsBean.getAsr() +" in "+ dateBean.getGregorian().getDate());

                        if (CURRENT_DATE.equals(dateBean.getGregorian().getDate())){
                            mDay.setText(dateBean.getGregorian().getWeekday().getEn());
                            mFajrTime.setText(dataBean.getTimings().getFajr());
                            mDhuhrTime.setText(dataBean.getTimings().getDhuhr());
                            mAsrTime.setText(dataBean.getTimings().getAsr());
                            mMaghribTime.setText(dataBean.getTimings().getMaghrib());
                            mIshaTime.setText(dataBean.getTimings().getIsha());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AdzanResponse> call, Throwable t) {

            }
        });

        AdzanService.getAPI()
                .getCurrentDate("Asia/Makassar").enqueue(new Callback<CurrentDateResponse>() {
            @Override
            public void onResponse(Call<CurrentDateResponse> call, Response<CurrentDateResponse> response) {
                CurrentDateResponse currentDateResponse = response.body();
                Log.d("Tanggal", currentDateResponse.getData());
                CURRENT_DATE = currentDateResponse.getData();
                mCurrentDate.setText(CURRENT_DATE);
            }

            @Override
            public void onFailure(Call<CurrentDateResponse> call, Throwable t) {

            }
        });

        AdzanService.getAPI().getCurrentTime("Asia/Makassar").enqueue(new Callback<CurrentTimeResponse>() {
            @Override
            public void onResponse(Call<CurrentTimeResponse> call, Response<CurrentTimeResponse> response) {
                CurrentTimeResponse currentTimeResponse = response.body();
                Log.d("Jam", currentTimeResponse.getData());
                mCurrentTime.setText(currentTimeResponse.getData());
            }

            @Override
            public void onFailure(Call<CurrentTimeResponse> call, Throwable t) {

            }
        });
    }
}
