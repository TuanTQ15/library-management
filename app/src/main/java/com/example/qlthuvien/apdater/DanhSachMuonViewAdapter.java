package com.example.qlthuvien.apdater;

import android.graphics.Color;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qlthuvien.R;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.database.AppDatabase;
import com.example.qlthuvien.model.ChiTietMuon;

import java.util.List;

public class DanhSachMuonViewAdapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final List<ChiTietMuon> listLoanBook;
    private DetailUsedDao detailUsedDao;
    public DanhSachMuonViewAdapter(List<ChiTietMuon> listLoanBook) {
        this.listLoanBook = listLoanBook;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.muon_sach_view, null);
        } else viewProduct = convertView;
        //Bind sữ liệu phần tử vào View
        ChiTietMuon chiTietMuon = (ChiTietMuon) getItem(position);
        String path = Environment.getExternalStorageDirectory().getPath();
        detailUsedDao=getDetailUsedDao();
        ((TextView) viewProduct.findViewById(R.id.loan_idPhieuMuon)).setText(String.format("Số Phiếu: %d", chiTietMuon.getLoanId()));
        ((TextView) viewProduct.findViewById(R.id.loan_idDG)).setText(String.format("Mã DG: %d", chiTietMuon.getReaderId()));
        ((TextView) viewProduct.findViewById(R.id.loan_tenDG)).setText("Tên DG:"+detailUsedDao.getReaderName(chiTietMuon.getReaderId()));
        ((TextView) viewProduct.findViewById(R.id.loan_ngayMuon)).setText("Ngày Mượn: " + chiTietMuon.getDayLoan()+"/"+ chiTietMuon.getMonthLoan()
                +"/"+ chiTietMuon.getYearLoan());
        TextView txtTrangThai = viewProduct.findViewById(R.id.txt_trang_thai);
        if(chiTietMuon.getStatus()==1){
            txtTrangThai.setText("Đã Trả");
            txtTrangThai.setTextColor(Color.GREEN);
        }else {
            txtTrangThai.setText("Chưa Trả");
            txtTrangThai.setTextColor(Color.RED);
        }
        Animation animation = AnimationUtils.loadAnimation(viewProduct.getContext(),R.anim.slide_left);
        viewProduct.startAnimation(animation);
        return viewProduct;
    }

    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listLoanBook.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listLoanBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listLoanBook.get(position).getLoanId();
    }
    public DetailUsedDao getDetailUsedDao() {
        if(detailUsedDao == null){
            detailUsedDao = AppDatabase.getInstanceDetailUsedDao();
        }
        return detailUsedDao;
    }
}
