package com.example.nhom_8.Dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.nhom_8.Object.HoaDon;
import com.example.nhom_8.Object.YeuThich;
import com.example.nhom_8.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    Connection connection;
    Context context;
    public HoaDonDao(Context context){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
        this.context = context;
    }
    public List<HoaDon> getAll(){
        List<HoaDon> list = new ArrayList<HoaDon>();

        try {
            if (this.connection != null) {

                String sqlQuery = "select hd.idHoaDon,an.tenDoAn, an.gia, hd.soLuong, hd.tongGia,hd.ngayMua,tv.tenNguoiDung from HoaDon hd\n" +
                        "  inner join DoAn an on hd.idDoAn = an.idDoAn\n" +
                        "  inner join ThanhVien tv on tv.idTV = hd.idTV" ;

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    HoaDon obj = new HoaDon();
                    obj.setIdHoaDon(resultSet.getInt("idHoaDon"));
                    obj.setTenMonAn(resultSet.getString("tenDoAn"));
                    obj.setGiaMonAn(resultSet.getInt("gia"));
                    obj.setSoLuong(resultSet.getInt("soLuong"));
                    obj.setTongGia(resultSet.getInt("tongGia"));
                    obj.setNgayMua(resultSet.getString("NgayMua"));
                    obj.setTenThanhVien(resultSet.getString("tenNguoiDung"));
                    list.add(obj);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu  " );
            e.printStackTrace();
        }

        return  list;
    }
    public void deleteRow(HoaDon obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                System.out.println(obj.getIdHoaDon());
                String sqlUpdate = "delete from HoaDon where idHoaDon = "+obj.getIdHoaDon()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "delete: finish delete");
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "delete: Có lỗi sửa dữ liệu " );
            Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
