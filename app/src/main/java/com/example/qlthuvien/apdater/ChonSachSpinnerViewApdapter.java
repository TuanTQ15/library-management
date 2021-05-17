package com.example.qlthuvien.apdater;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlthuvien.R;
import com.example.qlthuvien.model.Sach;

import java.util.List;

public class ChonSachSpinnerViewApdapter extends ArrayAdapter<Sach> {
    private List<Sach> dsSach;
    private List<Sach> dsDaChon;
    public ChonSachSpinnerViewApdapter(@NonNull Context context, List<Sach> dsSach, List<Sach> dsDaChon) {
        super(context, -1, dsSach);
        this.dsSach=dsSach;
        this.dsDaChon=dsDaChon;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.text_view, parent, false);
        TextView textView = row.findViewById(R.id.txtId);
        Sach sach = getItem(position);
        textView.setText(String.valueOf(sach.getBookId()));
        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.view_spinner_muon_sach, parent, false);
        ImageView imageView = row.findViewById(R.id.spinner_image_book);
        TextView textView = row.findViewById(R.id.textView_item_name);
        TextView txtSoLuong = row.findViewById(R.id.txt_soLuong);
        TextView txtTrangThai = row.findViewById(R.id.txt_trang_thai_chon);
        Sach sach = getItem(position);
        if(kiemTraTrangThai(sach)==true){
            txtTrangThai.setText("Đã Chọn");
            txtTrangThai.setTextColor(Color.GREEN);
        }else {
            txtTrangThai.setText("Chưa Chọn");
            txtTrangThai.setTextColor(Color.RED);
        }
        txtSoLuong.setText(String.valueOf(sach.getQuantity()));
        imageView.setImageURI(Uri.parse(sach.getImageLink()));
        textView.setText(sach.getBookName());
        return row;
    }
    private boolean kiemTraTrangThai(Sach current){
        for (Sach sach : dsDaChon) {
            if(current == sach){
                return true;
            }
        }
        return false;
    }
}