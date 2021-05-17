package com.example.qlthuvien.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qlthuvien.R;
import com.example.qlthuvien.activity.DocGia.DocGiaActivity;
import com.example.qlthuvien.activity.Sach.DanhSachMuonActivity;
import com.example.qlthuvien.activity.Sach.MuonSachActivity;
import com.example.qlthuvien.activity.Sach.SachActivity;
import com.example.qlthuvien.activity.ThongKe.ThongKeActivity;

public class MenuActivity extends AppCompatActivity {
    TextView ms;

    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        ms=findViewById(R.id.wlcmsg);
        ms.setText("Welcome, Admin");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                //new User(this).removeUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void muonSach(View view) {startActivity(new Intent(this, MuonSachActivity.class));    }


    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void dsBook(View view) {
        Intent intent=new Intent(this, SachActivity.class);
        startActivity(intent);
    }
    public void dsDG(View view) {
        Intent intent=new Intent(this, DocGiaActivity.class);
        startActivity(intent);
    }
    public void dsLoanBook(View view) {
        Intent intent=new Intent(this, DanhSachMuonActivity.class);
        startActivity(intent);
    }
    public void sachMuonNhieuNhat(View view) {
        Intent intent=new Intent(this, ThongKeActivity.class);
        startActivity(intent);
    }
}

