package com.kwiatko.zpo;

import java.util.Date;

public class DaneZPeselu {
    int day;
    int month;
    int year;
    Gender gender;

    public DaneZPeselu(int day, int month, int year, Gender gender) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender.toString() + "\n" + day + "-" + month + "-" + year;
    }

    enum Gender {
        Men,
        Women;
    };
}
