package com.example.qlthuvien.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlthuvien.R;
import com.example.qlthuvien.model.DocGia;

import java.util.List;

public class DocGiaSpinnerViewApdapter extends ArrayAdapter<DocGia> {
    public DocGiaSpinnerViewApdapter(@NonNull Context context, List<DocGia> dsDG) {
        super(context, 0, dsDG);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.text_view, parent, false);
        TextView textView = row.findViewById(R.id.txtId);
        DocGia docGia = getItem(position);
        textView.setText(String.valueOf(docGia.getReaderId()));

        return row;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_view_doc_gia, parent, false);
        DocGia docGia = getItem(position);
        TextView txtIdDG =  row.findViewById(R.id.spinner_id_DG);
        txtIdDG.setText(String.valueOf(docGia.getReaderId()));
        TextView txtTenDG = row.findViewById(R.id.spinner_tenDG);
        txtTenDG.setText(docGia.getReaderName());
        return row;
    }
}
