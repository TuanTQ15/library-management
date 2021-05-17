package com.example.qlthuvien.activity.Sach;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.Other.DisplayAlert;
import com.example.qlthuvien.R;
import com.example.qlthuvien.apdater.ChonSachSpinnerViewApdapter;
import com.example.qlthuvien.apdater.DocGiaSpinnerViewApdapter;
import com.example.qlthuvien.dao.BookDao;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.dao.ReaderDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.ChiTietMuon;
import com.example.qlthuvien.model.DocGia;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.SachMuon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MuonSachActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private DisplayAlert displayAlert;
    private DetailUsedDao detailUsedDao;
    private BookDao bookDao;
    private ReaderDao readerDao;
    private Spinner spinnerBook, spinnerReader;
    private TextView txtNgayMuon, txtSoPhieu, txtTenDG, txtQuyen1, txtQuyen2, txtQuyen3;
    private ChonSachSpinnerViewApdapter adapterBook;
    private DocGiaSpinnerViewApdapter adapterReader;
    private Sach sach, current;
    boolean isSpinnerTouch = false;
    private List<Sach> dsDaChon = new ArrayList<>(3);
    private ChiTietMuon chiTietMuonChuaTra;
    private DocGia docGia;
    final Calendar c = Calendar.getInstance();
    List<Sach> listSach;
    List<DocGia> listDocGia;
    List<ChiTietMuon> listChiTietMuon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        setContentView(R.layout.activity_muon_sach);
        spinnerBook = findViewById(R.id.spinner_book);
        spinnerReader = findViewById(R.id.spinner_readerid);
        txtNgayMuon = findViewById(R.id.txt_ngaymuon);
        txtSoPhieu = findViewById(R.id.txt_sophieu);
        txtTenDG = findViewById(R.id.txt_tendocgia);
        txtQuyen1 = findViewById(R.id.txt_quyen_1);
        txtQuyen2 = findViewById(R.id.txt_quyen_2);
        txtQuyen3 = findViewById(R.id.txt_quyen_3);
        builder = new AlertDialog.Builder(MuonSachActivity.this);
        readerDao = getReaderDao();
        detailUsedDao = getDetailUsedDao();
        bookDao = getBookDao();
        listSach = bookDao.getAll();
        listDocGia = readerDao.getAll();
        listChiTietMuon = detailUsedDao.getAll();
        adapterReader = new DocGiaSpinnerViewApdapter(this, listDocGia);
        spinnerReader.setAdapter(adapterReader);
        txtNgayMuon.setText(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
        if (listChiTietMuon.size() == 0) {
            txtSoPhieu.setText("1");
        } else {
            txtSoPhieu.setText(String.valueOf(listChiTietMuon.get(listChiTietMuon.size() - 1).getLoanId() + 1));
        }
    }

    private void setEvent() {
        spinnerReader.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                docGia = (DocGia) adapter.getItemAtPosition(i);
                txtTenDG.setText(docGia.getReaderName());
                chiTietMuonChuaTra = detailUsedDao.getChiTietMuon2(docGia.getReaderId(), 0);
                bookDao = getBookDao();
                listSach = bookDao.getAll();
                if (chiTietMuonChuaTra != null) {
                    List<SachMuon> list = chiTietMuonChuaTra.getListBook();
                    if (listSach != null) {

                        for (SachMuon s : list) {
                            for (int j = 0; j < listSach.size(); j++) {
                                if (s.getBookId() == listSach.get(j).getBookId()) {
                                    listSach.remove(j);
                                    j--;
                                }
                            }
                        }
                    }

                }
                adapterBook = new ChonSachSpinnerViewApdapter(MuonSachActivity.this, listSach, dsDaChon);
                spinnerBook.setAdapter(adapterBook);
                isSpinnerTouch = false;
                dsDaChon.clear();
                txtQuyen1.setText("");
                txtQuyen2.setText("");
                txtQuyen3.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        spinnerBook.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isSpinnerTouch = true;
                return false;
            }
        });
        spinnerBook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                if (isSpinnerTouch) {
                    current = (Sach) adapter.getSelectedItem();
                    if (current.getQuantity() == 0 && !kiemTraTrangThai(current)) {
                        builder.setTitle("Lỗi");
                        builder.setMessage("Sách bạn chọn đã hết!!!");
                        displayAlert = new DisplayAlert(builder, MuonSachActivity.this);
                        displayAlert.displayAlert(false);
                    } else {
                        if (dsDaChon.size() < 3 && !kiemTraTrangThai(current)) {
                            dsDaChon.add(current);
                            int index = dsDaChon.size() - 1;
                            if (index == 0) {
                                txtQuyen1.setText(dsDaChon.get(index).getBookName());
                            } else if (index == 1) {
                                txtQuyen2.setText(dsDaChon.get(index).getBookName());
                            } else {
                                txtQuyen3.setText(dsDaChon.get(index).getBookName());
                            }

                        } else if (kiemTraTrangThai(current)) {

                        } else {
                            builder.setTitle("Quá số lượng");
                            builder.setMessage("Bạn đã chọn 3 quyển sách!!!");
                            displayAlert = new DisplayAlert(builder, MuonSachActivity.this);
                            displayAlert.displayAlert(false);
                        }
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void huy(View view) {
        finish();
    }

    public void XacNhan(View view) {
        if(dsDaChon.size()>0){
            sach = (Sach) spinnerBook.getSelectedItem();
            DocGia docGia = (DocGia) spinnerReader.getSelectedItem();
            final String bookid = String.valueOf(sach.getBookId());
            final String readerId = String.valueOf(docGia.getReaderId());
            if (bookid.equals("") || readerId.equals("")) {
                builder.setTitle("Lỗi");
                builder.setMessage("Vui lòng điền đầy đủ thông tin");
                displayAlert = new DisplayAlert(builder, this);
                displayAlert.displayAlert(false);
            } else {

                try {
                    List<SachMuon> dsMuon = new ArrayList<>();
                    for (Sach sach : dsDaChon) {
                        SachMuon sachMuon = new SachMuon(sach.getBookId(), sach.getBookName(), sach.getImageLink());

                        dsMuon.add(sachMuon);
                        sach.setQuantity(sach.getQuantity() - 1);
                        sach.setNumberTimes(sach.getNumberTimes() + 1);
                        bookDao.update(sach);
                    }
                    ChiTietMuon chiTietMuon = new ChiTietMuon(dsMuon, Long.parseLong(readerId), c.get(Calendar.DAY_OF_MONTH),
                            c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
                    detailUsedDao.insert(chiTietMuon);
                    builder.setTitle("Mượn thành công");
                    builder.setMessage("\nTên DG: " + detailUsedDao.getReaderName(Long.parseLong(readerId)));
                    displayAlert = new DisplayAlert(builder, this);
                    displayAlert.displayAlert(true);
                } catch (Exception e) {
                    builder.setTitle("Mượn thất bại!!!");
                    builder.setMessage("Tên DG: " + detailUsedDao.getReaderName(Long.parseLong(readerId)));
                    displayAlert = new DisplayAlert(builder, this);
                    displayAlert.displayAlert(false);
                }
            }
        }else {
            builder.setTitle("Lỗi");
            builder.setMessage("Bạn chưa chọn sách");
            displayAlert = new DisplayAlert(builder, this);
            displayAlert.displayAlert(false);
        }
    }

    public ReaderDao getReaderDao() {
        if (readerDao == null) {
            readerDao = AppDatabase.getInstanceReaderDAO();
        }
        return readerDao;
    }

    public DetailUsedDao getDetailUsedDao() {
        if (detailUsedDao == null) {
            detailUsedDao = AppDatabase.getInstanceDetailUsedDao();
        }
        return detailUsedDao;
    }

    public BookDao getBookDao() {
        if (bookDao == null) {
            bookDao = AppDatabase.getInstanceBookDao();
        }
        return bookDao;
    }

    private boolean kiemTraTrangThai(Sach current) {
        for (Sach sach : dsDaChon) {
            if (current == sach) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
