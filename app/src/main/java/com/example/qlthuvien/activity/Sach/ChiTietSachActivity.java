package com.example.qlthuvien.activity.Sach;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlthuvien.Other.DisplayAlert;
import com.example.qlthuvien.R;
import com.example.qlthuvien.dao.BookDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.Other.ImageUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ChiTietSachActivity extends AppCompatActivity {
    private Button btnXacNhan;
    private Sach sach;
    private ImageButton btn_imageBook;
    private EditText edtNameBook, edtSoLuong;;
    private TextView txtImage;
    private ImageView imageView;
    private BookDao bookDao;
    private String bookName, bookImageLink,soLuong;
    private Uri image = null;
    private AlertDialog.Builder builder;
    private DisplayAlert displayAlert;
    private static int REQUEST_CODE = 1999;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setControl();
        setEvent();
    }

    private void setControl() {
        setContentView(R.layout.activity_them_sach);
        builder = new AlertDialog.Builder(ChiTietSachActivity.this);
        btnXacNhan = findViewById(R.id.btn_addBook);
        edtNameBook = findViewById(R.id.add_TenSach);
        txtImage = findViewById(R.id.add_Link);
        btn_imageBook = findViewById(R.id.add_imageBook);
        imageView = findViewById(R.id.add_imageView);
        edtSoLuong =findViewById(R.id.edt_so_luong_sach);
        bookDao = getBookDao();
        if(getIntent().getExtras() != null) {
            sach = (Sach) getIntent().getSerializableExtra("book");
        }

    }

    private void setEvent() {
        edtNameBook.setText(sach.getBookName());
        imageView.setImageURI(Uri.parse(sach.getImageLink()));
        txtImage.setText(sach.getImageLink());
        edtSoLuong.setText(String.valueOf(sach.getQuantity()));
        btn_imageBook.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, REQUEST_CODE);
        });

        btnXacNhan.setOnClickListener(v -> {
            bookName = edtNameBook.getText().toString();
            bookImageLink = txtImage.getText().toString();
            soLuong = edtSoLuong.getText().toString();
            if (bookName.equals("")||bookImageLink.equals("")||soLuong.equals("")) {
                builder.setTitle("Lỗi");
                builder.setMessage("Vui lòng điền đầy đủ thông tin");
                displayAlert = new DisplayAlert(builder, this);
                displayAlert.displayAlert(false);
            }
            else {
                sach.setBookName(bookName);
                sach.setQuantity(Integer.valueOf(soLuong));
                if (image != null) {
                    sach.setImageLink(ImageUtils.saveImagePrivate(this, image).toString());
                }
                try {
                    bookDao.update(sach);
                    builder.setTitle("Sửa Thành Công");
                    builder.setMessage("Tên " + bookName);
                    displayAlert = new DisplayAlert(builder,this);
                    displayAlert.displayAlert(true);
                } catch (Exception e) {
                    builder.setTitle("Lỗi");
                    builder.setMessage("Sửa thất bại!!");
                    displayAlert = new DisplayAlert(builder,this);
                    displayAlert.displayAlert(true);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                image = imageUri;
                txtImage.setText(imageUri.toString());
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Có gì đó không đúng. Vui lòng kiểm tra lại!!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Bạn chưa chọn ảnh", Toast.LENGTH_LONG).show();
        }
    }

    public BookDao getBookDao() {
        if (bookDao == null) {
            bookDao = AppDatabase.getInstanceBookDao();
        }
        return bookDao;
    }
}
