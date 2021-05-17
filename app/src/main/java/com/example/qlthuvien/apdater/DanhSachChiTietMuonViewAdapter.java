package com.example.qlthuvien.apdater;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.qlthuvien.R;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.ChiTietMuon;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.SachMuon;

import java.util.List;

public class DanhSachChiTietMuonViewAdapter extends ArrayAdapter<SachMuon> {
    private DetailUsedDao detailUsedDao;
    public DanhSachChiTietMuonViewAdapter(@NonNull Context context, @NonNull List<SachMuon> dsSach) {
        super(context, 0, dsSach);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        detailUsedDao=getDetailUsedDao();
        View view = inflater.inflate(R.layout.sach_view, parent, false);
        SachMuon sach = (SachMuon) getItem(position);
        ImageView viewImage =  view.findViewById(R.id.imageBook);
        viewImage.setImageURI(Uri.parse(sach.getImageLink()));
        ((TextView) view.findViewById(R.id.idDG)).setText(String.format("ID: %d", sach.getBookId()));
        ((TextView) view.findViewById(R.id.tenSach)).setText(String.format("%s", sach.getBookName()));
        Animation animation = AnimationUtils.loadAnimation(view.getContext(),R.anim.scale);
        view.startAnimation(animation);
        return view;
    }
    public DetailUsedDao getDetailUsedDao() {
        if(detailUsedDao == null){
            detailUsedDao = AppDatabase.getInstanceDetailUsedDao();
        }
        return detailUsedDao;
    }
}
