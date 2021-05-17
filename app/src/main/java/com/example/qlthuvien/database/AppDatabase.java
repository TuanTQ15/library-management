package com.example.qlthuvien.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.qlthuvien.dao.BookDao;
import com.example.qlthuvien.dao.DetailUsedDao;
import com.example.qlthuvien.dao.ReaderDao;
import com.example.qlthuvien.model.DataConverter;
import com.example.qlthuvien.model.DocGia;
import com.example.qlthuvien.model.Sach;
import com.example.qlthuvien.model.ChiTietMuon;

import kotlin.jvm.Volatile;

@Database(entities = {Sach.class, ChiTietMuon.class, DocGia.class}, version = AppDatabase.VERSION)
@TypeConverters(DataConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao getBookDao();

    public abstract DetailUsedDao getDetailUsedDao();

    public abstract ReaderDao getReaderDao();

    protected static final int VERSION = 1;

    @Volatile
    private static AppDatabase INSTANCE = null;

    public static AppDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = build(MyApplication.getMyContext());
        }
        return INSTANCE;
    }

    public static BookDao getInstanceBookDao(){
        return getInstance().getBookDao();
    }
    public static ReaderDao getInstanceReaderDAO(){
        return getInstance().getReaderDao();
    }
    public static DetailUsedDao getInstanceDetailUsedDao(){
        return getInstance().getDetailUsedDao();
    }

    private static final String DB_NAME = "QLThuVien";

    private static AppDatabase build(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
    }
}
