package com.example.qlthuvien.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "detail_used")
public class ChiTietMuon implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "loan_id")
    private long loanId;
    @ColumnInfo(name = "reader_id")
    private long readerId;
    @ColumnInfo(name ="list_book")
    private List<SachMuon> listBook;
    @ColumnInfo(name = "date_load")
    private int dayLoan;
    @ColumnInfo(name = "month_load")
    private int monthLoan;
    @ColumnInfo(name = "year_load")
    private int yearLoan;
    @ColumnInfo(name ="status")
    private int status = 0;
    public ChiTietMuon(List<SachMuon> listBook, long readerId, int dayLoan, int monthLoan, int yearLoan) {
        this.listBook=listBook;
        this.readerId = readerId;
        this.dayLoan = dayLoan;
        this.monthLoan = monthLoan;
        this.yearLoan = yearLoan;
    }
    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }


    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public int getDayLoan() {
        return dayLoan;
    }

    public void setDayLoan(int dayLoan) {
        this.dayLoan = dayLoan;
    }

    public int getMonthLoan() {
        return monthLoan;
    }

    public void setMonthLoan(int monthLoan) {
        this.monthLoan = monthLoan;
    }

    public int getYearLoan() {
        return yearLoan;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SachMuon> getListBook() {
        return listBook;
    }

    public void setListBook(List<SachMuon> listBook) {
        this.listBook = listBook;
    }

    public void setYearLoan(int yearLoan) {
        this.yearLoan = yearLoan;
    }
}
