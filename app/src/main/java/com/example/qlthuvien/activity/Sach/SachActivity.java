package com.example.qlthuvien.activity.Sach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.R;
import com.example.qlthuvien.apdater.DanhSachSachViewAdapter;
import com.example.qlthuvien.dao.BookDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
    private BookDao bookDao;
    List<Sach> listSach;
    ListView listViewBook;
    DanhSachSachViewAdapter DanhSachSachViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        setControl();
        setEvent();
    }

    private void setControl(){
        listSach = new ArrayList<>();
        listViewBook = findViewById(R.id.listSach);
        bookDao = getBookDao();
    }
    private void setEvent(){
        listViewBook.setOnItemLongClickListener((parent, view, position, id) -> {
            Sach sach = listSach.get(position);
            PopupMenu popupMenu = new PopupMenu(this, view);
            popupMenu.inflate(R.menu.menu);
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menuItem_delete:
                        listSach.remove(sach);
                        DanhSachSachViewAdapter.notifyDataSetChanged();
                        bookDao.delete(sach);
                        break;
                    case R.id.menuItem_edit:
                        XemChiTietSach(view,sach);
                }
                return true;
            });
            popupMenu.show();
            return false;
        });
    }
    public BookDao getBookDao() {
        if (bookDao == null) {
            bookDao = AppDatabase.getInstanceBookDao();
        }
        return bookDao;
    }

    public void themBook(View view) {
        Intent intent = new Intent(this, ThemSachActivity.class);
        startActivity(intent);
    }
    public void XemChiTietSach(View view, Sach sach) {
        Intent intent=new Intent(this, ChiTietSachActivity.class);
        intent.putExtra("book", sach);
        startActivity(intent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        listSach = bookDao.getAll();
        DanhSachSachViewAdapter = new DanhSachSachViewAdapter(listSach);
        listViewBook.setAdapter(DanhSachSachViewAdapter);
        listViewBook.deferNotifyDataSetChanged();
    }
}
