package com.example.qlthuvien.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(tableName = "listBookLoan")
public class SachMuon implements Serializable {
    @ColumnInfo(name="book_name")
    private String bookName;
    @ColumnInfo(name="book_id")
    private long bookId;
    @ColumnInfo(name = "image_link")
    private String imageLink="";

    public SachMuon(long bookId,String bookName , String imageLink) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.imageLink = imageLink;
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

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
