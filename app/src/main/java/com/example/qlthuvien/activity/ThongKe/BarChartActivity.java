package com.example.qlthuvien.activity.ThongKe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.R;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.ThongKe;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {
    private List<Sach> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        Intent i = getIntent();
        list = (List<Sach>) i.getSerializableExtra("listStatistic");
        int khoangCach = 0;
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList();
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        for (Sach sach : list) {
            labels.add(String.valueOf(sach.getBookId()));
            entries.add(new BarEntry(khoangCach, sach.getNumberTimes()));
            khoangCach = khoangCach + 1;
        }

        BarDataSet barDataset = new BarDataSet(entries, "");
        barDataset.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataset.setValueTextColor(Color.BLACK);
        barChart.animateY(500);
        barDataset.setValueTextSize(16f);
        BarData barData = new BarData(barDataset);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setLabelCount(barData.getEntryCount());
        barChart.animateY(1000);
        barChart.setFitBars(true);
        barChart.setData(barData);
    }


}
