package com.example.qlthuvien.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.SachMuon;

import java.util.List;

@Dao
public interface SachMuonDao {
    @Query("SELECT * FROM listBookLoan")
    List<SachMuon> getAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SachMuon sach);

    @Update
    void update(SachMuon sach);

    @Delete
    void delete(SachMuon sach);
}
