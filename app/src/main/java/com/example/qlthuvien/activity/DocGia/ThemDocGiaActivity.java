package com.example.qlthuvien.activity.DocGia;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

//2
public class ThemDocGiaActivity extends AppCompatActivity {
    private Button reg_btn;
    private TextView txtNgaySinh;
    private EditText edtName,edtSDT;
    private String name,sdt,ngaySinh;
    private RadioButton btnNam;
    private RadioGroup radioGroup;
    private ImageButton btnNgaySinh;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;
    private ReaderDao readerDao;
    private DocGia docGia;
    private AlertDialog.Builder builder;
    private DisplayAlert displayAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setControl();
        setEvent();
    }

    private void setEvent() {
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fetch the values.
                name = edtName.getText().toString();
                sdt = edtSDT.getText().toString();
                ngaySinh = txtNgaySinh.getText().toString();
                if (name.equals("")||ngaySinh.equals("")||sdt.equals("")){
                    builder.setTitle("Lỗi");
                    builder.setMessage("Vui lòng điền đầy đủ thông tin");
                    displayAlert = new DisplayAlert(builder,ThemDocGiaActivity.this);
                    displayAlert.displayAlert(false);
                } else{
                    int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.add_rdNam) {
                        docGia = new DocGia(name, lastSelectedDayOfMonth, lastSelectedMonth, lastSelectedYear, sdt, DocGia.Gender.MALE);
                    } else if (checkedRadioButtonId == R.id.add_rdNu) {
                        docGia = new DocGia(name, lastSelectedDayOfMonth, lastSelectedMonth, lastSelectedYear, sdt, DocGia.Gender.FEMALE);
                    }

                    try {
                        readerDao.insert(docGia);
                        builder.setTitle("Thêm Thành Công");
                        builder.setMessage("Tên " + name + ", SĐT " + sdt);
                        displayAlert = new DisplayAlert(builder,ThemDocGiaActivity.this);
                        displayAlert.displayAlert(true);
                    } catch (Exception e) {
                        builder.setTitle("Lỗi");
                        builder.setMessage("Thêm thất bại!!");
                        displayAlert = new DisplayAlert(builder,ThemDocGiaActivity.this);
                        displayAlert.displayAlert(true);
                    }
                }
            }
        });
    }

    private void setControl(){
        setContentView(R.layout.activity_them_doc_gia);
        reg_btn = (Button)findViewById(R.id.btn_reg);
        edtName = (EditText)findViewById(R.id.reg_Ten);
        edtSDT = (EditText)findViewById(R.id.reg_mob);
        btnNgaySinh = findViewById(R.id.add_btnNgaySinh);
        txtNgaySinh = findViewById(R.id.add_ngaySinh);
        btnNam = findViewById(R.id.add_rdNam);
        radioGroup=findViewById(R.id.add_rdGrGoiTinh);
        btnNam.setChecked(true);
        readerDao=getReaderDao();
        builder = new AlertDialog.Builder(ThemDocGiaActivity.this);
        this.btnNgaySinh.setOnClickListener(v -> buttonSelectDate());
        final Calendar c = Calendar.getInstance();
        this.lastSelectedYear = c.get(Calendar.YEAR);
        this.lastSelectedMonth = c.get(Calendar.MONTH);
        this.lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        txtNgaySinh.setText(lastSelectedYear + "/" + (lastSelectedMonth + 1) + "/" + lastSelectedDayOfMonth);
    }
    private void buttonSelectDate() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                txtNgaySinh.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear+1;
                lastSelectedDayOfMonth = dayOfMonth;
            }
        };

        DatePickerDialog datePickerDialog ;

        datePickerDialog = new DatePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        datePickerDialog.show();
    }
    public ReaderDao getReaderDao() {
        if(readerDao == null){
            readerDao = AppDatabase.getInstanceReaderDAO();
        }
        return readerDao;
    }
}