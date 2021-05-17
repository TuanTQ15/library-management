package com.example.qlthuvien.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "readers")
public class DocGia implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "reader_id")
    private  long readerId;
    @ColumnInfo(name = "reader_name")
    private String readerName;
    @ColumnInfo(name = "birth_day")
    private int day;
    @ColumnInfo(name = "month")
    private int month;
    @ColumnInfo(name = "year")
    private int year;
    @ColumnInfo(name = "sdt")
    private String sdt;
    @ColumnInfo(name="gender")
    public Gender gender;
    public enum Gender{
        MALE, FEMALE
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public DocGia(String readerName, int day, int month, int year, String sdt, Gender gender) {
        this.readerName = readerName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.sdt = sdt;
        this.gender = gender;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }


    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
