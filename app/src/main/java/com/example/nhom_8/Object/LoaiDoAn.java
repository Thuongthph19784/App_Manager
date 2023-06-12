package com.example.nhom_8.Object;

public class LoaiDoAn {
    private int idLoaiDoAn;
    private String tenLoaiDoAN;
    private int idQTV;

    public LoaiDoAn() {
    }

    public int getIdLoaiDoAn() {
        return idLoaiDoAn;
    }

    public void setIdLoaiDoAn(int idLoaiDoAn) {
        this.idLoaiDoAn = idLoaiDoAn;
    }

    public String getTenDoAn() {
        return tenLoaiDoAN;
    }

    public void setTenLoaiDoAn(String tenLoaiDoAN) {
        this.tenLoaiDoAN = tenLoaiDoAN;
    }

    public int getIdQTV() {
        return idQTV;
    }

    public void setIdQTV(int idQTV) {
        this.idQTV = idQTV;
    }

    @Override
    public String toString() {
        return "LoaiDoAn{" +
                "idLoaiDoAn=" + idLoaiDoAn +
                ", tenLoaiDoAN='" + tenLoaiDoAN + '\'' +
                ", idQTV=" + idQTV +
                '}';
    }
}
