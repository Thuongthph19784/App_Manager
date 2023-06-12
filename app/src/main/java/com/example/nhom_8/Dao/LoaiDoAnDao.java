package com.example.nhom_8.Dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.LoaiDoAn;
import com.example.nhom_8.Object.YeuThich;
import com.example.nhom_8.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoaiDoAnDao {
    Connection connection;
    Context context;
    public LoaiDoAnDao(Context context){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
        this.context = context;
    }
    public List<String> getAllTenLoaiDoAn(){
        List<String> list = new ArrayList<>();

        try {
            if (this.connection != null) {

                String sqlQuery = "select tenLoai from LoaiDoAn";

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    list.add(resultSet.getString("tenLoai"));
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  list;
    }
    public List<LoaiDoAn> getAll(){
        List<LoaiDoAn> ls = new ArrayList<>();
        try {
            if (this.connection != null) {

                String sqlQuery = "select * from LoaiDoAn";

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    LoaiDoAn loaiDoAn = new LoaiDoAn();
                    loaiDoAn.setIdLoaiDoAn(resultSet.getInt("idLoaiDoAn"));
                    loaiDoAn.setTenLoaiDoAn(resultSet.getString("tenLoai"));
                    loaiDoAn.setIdQTV(1);
                    ls.add(loaiDoAn);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  ls;
    }
    public void insertRow (LoaiDoAn obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String insertSQL = "insert into LoaiDoAN values (N'"+obj.getTenDoAn()+"',1)";

                String generatedColumns[] = { "idLoaiDoAn" };
                PreparedStatement stmtInsert = this.connection.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();
                Log.d("zzzzz", "insertRow: finish insert");
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
    }
    public void updateRow(LoaiDoAn obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "update LoaiDoAn set tenLoai = N'"+obj.getTenDoAn()+"',idQTV = 1 where idLoaiDoAn = "+obj.getIdLoaiDoAn()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
    public void deleteRow(LoaiDoAn obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "delete from LoaiDoAn where idLoaiDoAn = "+obj.getIdLoaiDoAn()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                Log.d("zzzzz", "delete: finish delete");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "delete: Có lỗi sửa dữ liệu " );
            Toast.makeText(context, "Đang có đồ ăn, không thể xóa", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
