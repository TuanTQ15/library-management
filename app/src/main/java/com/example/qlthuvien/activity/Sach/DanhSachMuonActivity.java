package com.example.qlthuvien.activity.Sach;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.R;
import com.example.qlthuvien.apdater.DanhSachMuonViewAdapter;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.ChiTietMuon;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMuonActivity extends AppCompatActivity {
    private List<ChiTietMuon> listLoanBook;
    private DetailUsedDao detailUsedDao;
    private ListView listViewBook;
    private DanhSachMuonViewAdapter DanhSachMuonViewAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        setContentView(R.layout.activity_ds_muon);
        listViewBook = findViewById(R.id.loan_listBook);
    }
    private void setEvent() {
        listViewBook.setOnItemClickListener((parent, view, position, id) -> {
            ChiTietMuon chiTietMuon = (ChiTietMuon) DanhSachMuonViewAdapter.getItem(position);
            Intent intent=new Intent(this, ChiTietMuonActivity.class);
            intent.putExtra("detail_used", chiTietMuon);
            startActivity(intent);
        });
    }

    public DetailUsedDao getDetailUsedDao() {
        if(detailUsedDao == null){
            detailUsedDao = AppDatabase.getInstanceDetailUsedDao();
        }
        return detailUsedDao;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listLoanBook = new ArrayList<>();
        detailUsedDao=getDetailUsedDao();
        listLoanBook = detailUsedDao.getAll();
        DanhSachMuonViewAdapter = new DanhSachMuonViewAdapter(listLoanBook);
        listViewBook.setAdapter(DanhSachMuonViewAdapter);
    }
}
