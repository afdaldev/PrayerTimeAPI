package com.example.adzan;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.example.adzan.data.model.AdzanResponse;
import com.example.adzan.data.model.AdzanService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdzanService.getAPI().getPrayerTimeByCity(
                "Makassar", "ID").enqueue(new Callback<AdzanResponse>() {

            @Override
            public void onResponse(Call<AdzanResponse> call, Response<AdzanResponse> response) {
                if(response.isSuccessful()){
                    List<AdzanResponse.DataBean> dataBeans = response.body().getData();

                    for (AdzanResponse.DataBean dataBean: dataBeans){
                        AdzanResponse.DataBean.DateBean dateBean = dataBean.getDate();
                        AdzanResponse.DataBean.TimingsBean timingsBean = dataBean.getTimings();
                        Log.d("Jadwal Shalat Subuh", timingsBean.getFajr() + " in " + dateBean.getReadable());
                    }
                }
            }

            @Override
            public void onFailure(Call<AdzanResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

        AdzanService.getAPI().getPrayerTimeHijriCalendarByCity("Makassar", "ID").enqueue(new Callback<AdzanResponse>() {
            @Override
            public void onResponse(Call<AdzanResponse> call, Response<AdzanResponse> response) {
                List<AdzanResponse.DataBean> dataBeans = response.body().getData();

                for (AdzanResponse.DataBean dataBean: dataBeans){
                    AdzanResponse.DataBean.DateBean.HijriBean hijriBean = dataBean.getDate().getHijri();
                    AdzanResponse.DataBean.TimingsBean timingsBean = dataBean.getTimings();

                    Log.d("Jadwal Shalat Zuhur", timingsBean.getDhuhr() + " in " + hijriBean.getDate());
                }
            }

            @Override
            public void onFailure(Call<AdzanResponse> call, Throwable t) {

            }
        });

    }
}
