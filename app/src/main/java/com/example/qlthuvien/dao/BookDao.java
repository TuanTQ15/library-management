package com.example.qlthuvien.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.qlthuvien.model.Sach;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM books")
    List<Sach> getAll();
    @Query("select * from books where books.book_id=:bookId ")
    Sach getBook(long bookId);
    @Query("select  * from books order by books.number_times desc limit 10 ")
    List<Sach> getThongKe();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Sach sach);

    @Update
    void update(Sach sach);

    @Delete
    void delete(Sach sach);
}
