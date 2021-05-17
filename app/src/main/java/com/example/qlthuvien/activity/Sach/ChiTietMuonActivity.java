package com.example.qlthuvien.activity.Sach;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.Other.DisplayAlert;
import com.example.qlthuvien.R;
import com.example.qlthuvien.apdater.DanhSachChiTietMuonViewAdapter;
import com.example.qlthuvien.dao.BookDao;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.ChiTietMuon;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.SachMuon;

import java.util.List;

public class ChiTietMuonActivity extends AppCompatActivity {
    private ChiTietMuon chiTietMuonDangChon;
    private ListView listView;
    private DetailUsedDao detailUsedDao;
    private DanhSachChiTietMuonViewAdapter adapter;
    private List<SachMuon> dsSach;
    private BookDao bookDao;
    private TextView txtMaPhieu;
    private TextView txtMaDG;
    private TextView txtTenDG;
    private TextView txtNgayMuon;
    private Button btnTraSach, btnBaoMat;
    private String tenDG;
    private AlertDialog.Builder builder;
    private DisplayAlert displayAlert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        setContentView(R.layout.activity_chi_tiet_muon);
        if (getIntent().getExtras() != null)
            chiTietMuonDangChon = (ChiTietMuon) getIntent().getSerializableExtra("detail_used");
        bookDao = getBookDao();
        detailUsedDao = getDetailUsedDao();
        listView = findViewById(R.id.chi_tiet_ds_muon);
        txtMaPhieu = findViewById(R.id.chi_tiet_idPhieuMuon);
        txtMaDG = findViewById(R.id.chi_tiet_idDG);
        txtTenDG = findViewById(R.id.chi_tiet_tenDG);
        txtNgayMuon = findViewById(R.id.chi_tiet_ngayMuon);
        btnTraSach = findViewById(R.id.btn_tra_sach);
        if (chiTietMuonDangChon.getStatus() == 1) {
            btnTraSach.setEnabled(false);
        }
        tenDG = detailUsedDao.getReaderName(chiTietMuonDangChon.getReaderId());
        builder = new AlertDialog.Builder(this);
    }
    private void setEvent() {
        txtMaPhieu.setText(String.valueOf(chiTietMuonDangChon.getLoanId()));
        txtMaDG.setText(String.valueOf(chiTietMuonDangChon.getReaderId()));
        txtTenDG.setText(tenDG);
        txtNgayMuon.setText(chiTietMuonDangChon.getDayLoan() + "/" + chiTietMuonDangChon.getMonthLoan()
                + "/" + chiTietMuonDangChon.getYearLoan());
        btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietMuon chiTietMuon = detailUsedDao.getchiTietMuon(chiTietMuonDangChon.getLoanId());
                try {
                    chiTietMuon.setStatus(1);
                    Sach temp;
                    for (SachMuon s : dsSach) {
                        temp = bookDao.getBook(s.getBookId());
                        temp.setQuantity(temp.getQuantity() + 1);
                        bookDao.update(temp);
                    }
                    detailUsedDao.update(chiTietMuon);
                    builder.setTitle("Thành công");
                    builder.setMessage("Trả sách thành công");
                    displayAlert = new DisplayAlert(builder, ChiTietMuonActivity.this);
                    displayAlert.displayAlert(true);
                } catch (Exception e) {
                    builder.setTitle("Lỗi");
                    builder.setMessage("Trả sách thất bại. Vui lòng thử lại!");
                    displayAlert = new DisplayAlert(builder, ChiTietMuonActivity.this);
                    displayAlert.displayAlert(true);
                }
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        dsSach = chiTietMuonDangChon.getListBook();
        adapter = new DanhSachChiTietMuonViewAdapter(this, dsSach);
        listView.setAdapter(adapter);
    }

    public DetailUsedDao getDetailUsedDao() {
        if (detailUsedDao == null) detailUsedDao = AppDatabase.getInstanceDetailUsedDao();
        return detailUsedDao;
    }

    public BookDao getBookDao() {
        if (bookDao == null) bookDao = AppDatabase.getInstanceBookDao();
        return bookDao;
    }
}
