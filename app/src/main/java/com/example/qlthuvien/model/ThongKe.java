package com.example.qlthuvien.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThongKe implements Serializable {
    private long book_id;
    private  String book_name;
    private int soLan;
}
