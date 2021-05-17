package com.example.qlthuvien.activity.ThongKe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.R;
import com.example.qlthuvien.activity.ThongKe.BarChartActivity;
import com.example.qlthuvien.apdater.BarChartViewAdapter;
import com.example.qlthuvien.dao.BookDao;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.ChiTietMuon;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.ThongKe;

import java.io.Serializable;
import java.util.List;

public class ThongKeActivity extends AppCompatActivity {
    private BookDao bookDao;
    private BarChartViewAdapter BarChartViewAdapter;
    private ListView listViewStatistic;
    private List<Sach> thongKeList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        bookDao=getBookDao();
        thongKeList = bookDao.getThongKe();
        BarChartViewAdapter = new BarChartViewAdapter(thongKeList);
        listViewStatistic = findViewById(R.id.listThongKe);
        listViewStatistic.setAdapter(BarChartViewAdapter);
    }
    public void xemBieuDo(View view) {
        Intent intent=new Intent(this, BarChartActivity.class);
        intent.putExtra("listStatistic",(Serializable) thongKeList);
        startActivity(intent);
    }
    public BookDao getBookDao() {
        if (bookDao == null) {
            bookDao = AppDatabase.getInstanceBookDao();
        }
        return bookDao;
    }
}
