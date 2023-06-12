package com.example.nhom_8.Dao;

import android.util.Log;

import com.example.nhom_8.Object.Top;
import com.example.nhom_8.Object.YeuThich;
import com.example.nhom_8.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    Connection connection;
    public ThongKeDao(){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public List<Top> getAllTop(){
        List<Top> list = new ArrayList<Top>();

        try {
            if (this.connection != null) {

                String sqlQuery = "select top 5 hd.idDoAn,da.tenDoAn,da.image, COUNT(hd.idDoAn) as soLanBanDuoc,SUM(hd.soLuong) as soLuongDaBan\n" +
                        "  from HoaDon hd\n" +
                        "  inner join DoAn da on hd.idDoAn = da.idDoAn\n" +
                        "  group by hd.idDoAn,da.tenDoAn,da.image order by soLanBanDuoc desc";

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    Top obj = new Top();
                    obj.setIdDoAn(resultSet.getInt("idDoAn"));
                    obj.setTenDoAn(resultSet.getString("tenDoAn"));
                    obj.setImage(resultSet.getString("image"));
                    obj.setSoLanBanDuoc(resultSet.getInt("soLanBanDuoc"));
                    obj.setSoLuongDaBan(resultSet.getInt("soLuongDaBan"));
                    list.add(obj);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu  " );
            e.printStackTrace();
        }

        return  list;
    }
    public int getDoanhThu(String tuNgay, String denNgay){
        int doanhThu = 0;
        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlQuery = "select SUM(tongGia) as DoanhThu from HoaDon where ngayMua between '"+tuNgay+"' and '"+denNgay+"'";


                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
                doanhThu = resultSet.getInt("DoanhThu");
                Log.d("zzzzz", "delete: finish delete");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "delete: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
        return doanhThu;
    }
}
