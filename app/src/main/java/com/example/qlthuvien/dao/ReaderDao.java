package com.example.qlthuvien.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.qlthuvien.model.DocGia;

import java.util.List;

@Dao
public interface ReaderDao {
    @Query("SELECT * FROM readers")
    List<DocGia> getAll();

    @Insert
    void insert(DocGia detailUsed);

    @Update
    void update(DocGia detailUsed);

    @Delete
    void delete(DocGia detailUsed);
}
