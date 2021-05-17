package com.example.qlthuvien.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "books")
public class Sach implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    private long bookId;
    @ColumnInfo(name = "book_name")
    private String bookName;
    @ColumnInfo(name = "image_link")
    private String imageLink="";
    @ColumnInfo(name="quantity")
    private int quantity;
    @ColumnInfo(name="number_times")
    private int numberTimes;
    @ColumnInfo(name = "status")
    public Status status = Status.NORMAL;
    public enum Status{
        LOST, NORMAL, BORROW
    }

    public int getNumberTimes() {
        return numberTimes;
    }

    public void setNumberTimes(int numberTimes) {
        this.numberTimes = numberTimes;
    }

    public Sach(String bookName, String imageLink, int quantity, Status status) {
        this.bookName = bookName;
        this.imageLink = imageLink;
        this.status = status;
        this.quantity =quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
