package com.example.qlthuvien.activity.DocGia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.R;
import com.example.qlthuvien.apdater.DanhSachDocGiaViewAdapter;
import com.example.qlthuvien.dao.ReaderDao;
import com.example.qlthuvien.model.DocGia;

import java.util.ArrayList;
import java.util.List;

public class DocGiaActivity extends AppCompatActivity {
    ListView listViewDG;
    private ReaderDao readerDao;
    List <DocGia> listDG;
    public static DanhSachDocGiaViewAdapter danhSachDocGiaViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docgia);
        setControl();
        setEvent();
    }
    private void setControl(){
        listDG = new ArrayList<>();
        listViewDG = findViewById(R.id.listDG);
    }
    private void setEvent(){
        listViewDG.setOnItemLongClickListener((parent, view, position, id) -> {

            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.inflate(R.menu.menu);
            DocGia docGia = listDG.get(position);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menuItem_delete:
                        listDG.remove(docGia);
                        danhSachDocGiaViewAdapter.notifyDataSetChanged();
                        readerDao.delete(docGia);
                        break;
                    case R.id.menuItem_edit:
                        XemChiTietDG(view, docGia);
                }
                return true;
            });
            popupMenu.show();
            return false;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        readerDao=getReaderDao();
        listDG = readerDao.getAll();
        danhSachDocGiaViewAdapter = new DanhSachDocGiaViewAdapter(listDG);
        listViewDG.setAdapter(danhSachDocGiaViewAdapter);
        listViewDG.deferNotifyDataSetChanged();
    }

    public ReaderDao getReaderDao() {
        if(readerDao == null){
            readerDao = AppDatabase.getInstanceReaderDAO();
        }
        return readerDao;
    }

    public void XemChiTietDG(View view, DocGia docGia) {
        Intent intent=new Intent(this, ChiTietDocGiaActivity.class);
        intent.putExtra("reader", docGia);
        startActivity(intent);
    }
    public void themDG(View view) {
        Intent intent=new Intent(this, ThemDocGiaActivity.class);
        startActivity(intent);
    }
}
