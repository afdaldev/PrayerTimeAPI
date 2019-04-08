package com.example.adzan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.adzan.adapter.PrayerTimesAdapter;
import com.example.adzan.data.model.AdzanResponse;
import com.example.adzan.data.model.AdzanService;

import java.util.List;

import static com.example.adzan.data.model.AdzanResponse.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PrayerTimesAdapter adapter;
    private List<DataBean> dataBeanList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_prayer_time_by_city);
        progressBar = findViewById(R.id.pb_prayer_time_by_city);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);

        AdzanService.getAPI().getPrayerTimeByCity(
                "Makassar", "ID").enqueue(new Callback<AdzanResponse>() {

            @Override
            public void onResponse(Call<AdzanResponse> call, Response<AdzanResponse> response) {

                dataBeanList = response.body().getData();
                adapter = new PrayerTimesAdapter(dataBeanList);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.INVISIBLE);

//                if(response.isSuccessful()){
////                    List<AdzanResponse.DataBean> dataBeans = response.body().getData();
////
////                    for (AdzanResponse.DataBean dataBean: dataBeans){
////                        AdzanResponse.DataBean.DateBean dateBean = dataBean.getDate();
////                        AdzanResponse.DataBean.TimingsBean timingsBean = dataBean.getTimings();
////                        Log.d("Jadwal Shalat Subuh", timingsBean.getFajr() + " in " + dateBean.getReadable());
////                    }
//                }
            }

            @Override
            public void onFailure(Call<AdzanResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
