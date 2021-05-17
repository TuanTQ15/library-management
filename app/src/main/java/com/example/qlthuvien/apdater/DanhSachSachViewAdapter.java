package com.example.qlthuvien.apdater;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.model.Sach;

import java.util.List;

public class DanhSachSachViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final List<Sach> listSach;
    public DanhSachSachViewAdapter(List<Sach> listSach) {
        this.listSach = listSach;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.sach_view, null);
        } else viewProduct = convertView;
        Sach sach = (Sach) getItem(position);
        ImageView viewImage = (ImageView) viewProduct.findViewById(R.id.imageBook);
        viewImage.setImageURI(Uri.parse(sach.getImageLink()));
        ((TextView) viewProduct.findViewById(R.id.idDG)).setText(String.format("ID: %d", sach.getBookId()));
        ((TextView) viewProduct.findViewById(R.id.tenSach)).setText(String.format("%s", sach.getBookName()));
        ((TextView) viewProduct.findViewById(R.id.txt_so_luong_sach)).setText("Số lượng: "+sach.getQuantity());
        Animation animation = AnimationUtils.loadAnimation(viewProduct.getContext(),R.anim.slide_left);
        viewProduct.startAnimation(animation);
        return viewProduct;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listSach.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listSach.get(position).getBookId();
    }
}
