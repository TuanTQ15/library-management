package com.example.qlthuvien.apdater;

import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.ThongKe;

import java.util.List;

public class BarChartViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final List<Sach> listThongKe;
    private TextView txtIdSach,txtTenSach,txtSoLan;
    public BarChartViewAdapter(List<Sach> listThongKe) {
        this.listThongKe = listThongKe;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.activity_muon_nhieu_nhat, null);
        } else viewProduct = convertView;
        //Bind sữ liệu phần tử vào View
        String path = Environment.getExternalStorageDirectory().getPath();
        Sach sach = (Sach) getItem(position);
        ImageView viewImage = (ImageView) viewProduct.findViewById(R.id.imageBook);
        viewImage.setImageURI(Uri.parse(sach.getImageLink()));
        txtIdSach=viewProduct.findViewById(R.id.top_idSach);
        txtTenSach=viewProduct.findViewById(R.id.top_tenSach);
        txtSoLan= viewProduct.findViewById(R.id.top_soLan);

        txtIdSach.setText("Mã Sách: "+ sach.getBookId());
        txtTenSach.setText(sach.getBookName());
        txtSoLan.setText(sach.getNumberTimes()+" lần mượn");
        Animation animation = AnimationUtils.loadAnimation(viewProduct.getContext(),R.anim.slide_left);
        viewProduct.startAnimation(animation);
        return viewProduct;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listThongKe.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listThongKe.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listThongKe.get(position).getBookId();
    }
}
