package com.example.qlthuvien.activity.DocGia;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.Other.DisplayAlert;
import com.example.qlthuvien.R;
import com.example.qlthuvien.dao.ReaderDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.DocGia;

import java.util.Calendar;

public class ChiTietDocGiaActivity extends AppCompatActivity {
    private EditText edtName, edtSDT;
    private ImageButton btnNgaySinh;
    private TextView txtNgaySinh;
    private RadioButton btnNam, btnNu;
    private AlertDialog.Builder builder;
    private DisplayAlert displayAlert;
    private RadioGroup radioGroup;
    private ReaderDao readerDao;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private DocGia docGia;
    private String name, sdt, ngaySinh;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        setContentView(R.layout.activity_chi_tiet_docgia);
        edtName = findViewById(R.id.edtName);
        edtSDT = findViewById(R.id.edtSDT);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        btnNgaySinh = findViewById(R.id.btnNgaySinh);
        btnNu = findViewById(R.id.rdNu);
        btnNam = findViewById(R.id.rdNam);
        radioGroup = findViewById(R.id.rdGrGoiTinh);
        readerDao = getReaderDao();
        builder = new AlertDialog.Builder(ChiTietDocGiaActivity.this);

    }

    private void setEvent() {
        if (getIntent().getExtras() != null) {
            docGia = (DocGia) getIntent().getSerializableExtra("reader");
            edtName.setText(docGia.getReaderName());
            edtSDT.setText(docGia.getSdt());
            txtNgaySinh.setText(docGia.getDay() + "/" + (docGia.getMonth()) + "/" + docGia.getYear());
            if (docGia.getGender() == DocGia.Gender.MALE) {
                btnNam.setChecked(true);
            } else {
                btnNu.setChecked(true);
            }
        }
        this.btnNgaySinh.setOnClickListener(v -> buttonSelectDate());
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = docGia.getYear();
        this.lastSelectedMonth = docGia.getMonth() - 1;
        this.lastSelectedDayOfMonth = docGia.getDay();
    }

    private void buttonSelectDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                txtNgaySinh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog;

        datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }

    public void LuuTTDG(View view) {
        name = edtName.getText().toString();
        sdt = edtSDT.getText().toString();
        ngaySinh = txtNgaySinh.getText().toString();
        if (name.equals("") || ngaySinh.equals("") || sdt.equals("")) {
            builder.setTitle("Lỗi");
            builder.setMessage("Vui lòng điền đầy đủ thông tin");
            displayAlert = new DisplayAlert(builder,this);
            displayAlert.displayAlert(false);
        } else {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.rdNam) {
                docGia.setReaderName(name);
                docGia.setDay(lastSelectedDayOfMonth);
                docGia.setMonth(lastSelectedMonth + 1);
                docGia.setYear(lastSelectedYear);
                docGia.setSdt(sdt);
                docGia.setGender(DocGia.Gender.MALE);
            } else if (checkedRadioButtonId == R.id.rdNu) {
                docGia.setReaderName(name);
                docGia.setDay(lastSelectedDayOfMonth);
                docGia.setMonth(lastSelectedMonth + 1);
                docGia.setYear(lastSelectedYear);
                docGia.setSdt(sdt);
                docGia.setGender(DocGia.Gender.FEMALE);
            }

            try {
                readerDao.update(docGia);
                builder.setTitle("Sửa Thành Công");
                builder.setMessage("Tên " + name + ", SĐT " + sdt);
                displayAlert = new DisplayAlert(builder,this);
                displayAlert.displayAlert(true);

            } catch (Exception e) {
                builder.setTitle("Lỗi");
                builder.setMessage("Sửa thất bại!!");
                displayAlert = new DisplayAlert(builder,this);
                displayAlert.displayAlert(false);
            }
        }
    }

    public void HuySuaTTDG(View view) {
        this.finish();
    }

    public ReaderDao getReaderDao() {
        if (readerDao == null) {
            readerDao = AppDatabase.getInstanceReaderDAO();
        }
        return readerDao;
    }

}
