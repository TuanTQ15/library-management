package com.example.qlthuvien.dao;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.qlthuvien.model.ChiTietMuon;
import com.example.qlthuvien.model.SachMuon;
import com.example.qlthuvien.model.ThongKe;
import com.example.qlthuvien.model.Sach;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DetailUsedDao {

    @Query("SELECT * FROM detail_used")
    List<ChiTietMuon> getAll();

    @Query("Select readers.reader_name from readers where  readers.reader_id = :readerId")
    String getReaderName(long readerId);
    @Insert
    void insert(ChiTietMuon chiTietMuon);

    @Update
    void update(ChiTietMuon chiTietMuon);

    @Delete
    void delete(ChiTietMuon chiTietMuon);
    @Query("select * from detail_used where detail_used.loan_id =:loandId and detail_used.status=0")
    ChiTietMuon getchiTietMuon (long loandId);
    @Query("select * from detail_used where detail_used.status=:status and detail_used.reader_id=:readerId ")
    ChiTietMuon getChiTietMuon2(long readerId,int status);
}
