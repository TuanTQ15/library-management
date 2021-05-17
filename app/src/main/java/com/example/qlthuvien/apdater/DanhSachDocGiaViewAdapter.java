package com.example.qlthuvien.apdater;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.model.DocGia;

import java.util.List;

public class DanhSachDocGiaViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final List<DocGia> listDG;
    public DanhSachDocGiaViewAdapter(List<DocGia> listDG) {
        this.listDG = listDG;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.docgia_view, null);
        } else viewProduct = convertView;
        //Bind sữ liệu phần tử vào View
        DocGia docGia = (DocGia) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.idDG)).setText("Mã ĐG: "+ docGia.getReaderId());
        ((TextView) viewProduct.findViewById(R.id.dgName)).setText("Tên ĐG: "+ docGia.getReaderName());
        Animation animation = AnimationUtils.loadAnimation(viewProduct.getContext(),R.anim.slide_left);
        viewProduct.startAnimation(animation);
        return viewProduct;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listDG.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listDG.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listDG.get(position).getReaderId();
    }
}
