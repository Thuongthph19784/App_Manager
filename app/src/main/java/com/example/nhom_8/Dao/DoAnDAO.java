package com.example.nhom_8.Dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.Object.YeuThich;
import com.example.nhom_8.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoAnDAO {
    Connection connection;
    Context context;
    public DoAnDAO(Context context){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
        this.context = context;
    }
    public List<DoAn> getAll(){
            List<DoAn> list = new ArrayList<DoAn>();

            try {
                if (this.connection != null) {

                    String sqlQuery = "select da.idDoAn,da.image,da.tenDoAn,da.gia,lda.tenLoai,da.idLoaiDoAN from DoAn da inner join LoaiDoAn lda on da.idLoaiDoAn = lda.idLoaiDoAn";

                    Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                    ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                    while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                        DoAn obj = new DoAn();
                        obj.setIdDoAn(resultSet.getInt("idDoAn")); // truyền tên cột dữ liệu
                        obj.setImage(resultSet.getString("image"));// tên cột dữ liệu là name
                        obj.setTenDoAn(resultSet.getString("tenDoAn"));
                        obj.setGia(resultSet.getInt("gia"));
                        obj.setTenLoaiDoAn(resultSet.getString("tenLoai"));
                        obj.setIdLoaiDoAN(resultSet.getInt("idLoaiDoAN"));
                        list.add(obj);
                    }
                } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



            } catch (Exception e) {
                Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
                e.printStackTrace();
            }

            return  list;
    }
    public void insertRow (DoAn obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String insertSQL = "insert into DoAn (tenDoAn,gia,image,idLoaiDoAn) values (N'"+obj.getTenDoAn()+"',"+obj.getGia()+",'"+obj.getImage()+"',"+obj.getIdLoaiDoAN()+")";

                String generatedColumns[] = { "idDoAn" };
                PreparedStatement stmtInsert = this.connection.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();

                Log.d("zzzzz", "insertRow: finish insert");
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
    }
    public void updateRow(DoAn obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "update DoAn set tenDoAn = N'"+obj.getTenDoAn()+"', gia = "+obj.getGia()+", image = '"+obj.getImage()+"' , idLoaiDoAn = "+obj.getIdLoaiDoAN()+" where idDoAn = "+obj.getIdDoAn()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
    public void deleteRow(DoAn obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "delete from DoAn where idDoAn = "+obj.getIdDoAn()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "delete: finish delete");
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "delete: Có lỗi sửa dữ liệu " );
            Toast.makeText(context, "Đang có hóa đơn hay yêu thích, không thể xóa", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
