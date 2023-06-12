package com.example.nhom_8.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Dao.ThanhVienDao;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.R;

import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    List<ThanhVien> ls;
    Context context;
    LayoutInflater inflater;
    ThanhVienDao dao;
    public ThanhVienAdapter(List<ThanhVien> ls, Context context) {
        this.ls = ls;
        this.context = context;
        dao = new ThanhVienDao(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quan_ly_tai_khoan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTaiKHoan.setText(ls.get(position).getUserName());
        holder.txtMatKhau.setText(ls.get(position).getPassWord());
        holder.txtTenNguoiDung.setText(ls.get(position).getTenNguoiDung());
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(holder.getAdapterPosition());
            }
        });
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(Gravity.BOTTOM,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTaiKHoan,txtMatKhau,txtTenNguoiDung;
        Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTaiKHoan = itemView.findViewById(R.id.item_quan_ly_tk_TxtTaiKhoan);
            txtMatKhau = itemView.findViewById(R.id.item_quan_ly_tk_TxtMatKhau);
            txtTenNguoiDung = itemView.findViewById(R.id.item_quan_ly_tk_TxtTenNguoiDung);
            btnSua = itemView.findViewById(R.id.item_quan_ly_tk_btnSua);
            btnXoa = itemView.findViewById(R.id.item_quan_ly_tk_btnXoa);

        }
    }
    public void delete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa");
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deleteRow(ls.get(position));
                loadData();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void update(int gravity,int position){
        LayoutInflater infl = ((Activity)context).getLayoutInflater();
        View v = infl.inflate(R.layout.update_tv, null);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        // set kích thước dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set vị trí dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        EditText edtTaiKhoan = v.findViewById(R.id.update_tv_edt_taiKhoan);
        EditText edtTenNguoiDung = v.findViewById(R.id.update_tv_edt_tenNguoiDung);
        EditText edtMatKhau = v.findViewById(R.id.update_tv_edt_matKhau);
        Button btnSua = v.findViewById(R.id.update_tv_btn_sua);

        edtTaiKhoan.setText(ls.get(position).getUserName());
        edtTenNguoiDung.setText(ls.get(position).getTenNguoiDung());
        edtMatKhau.setText(ls.get(position).getPassWord());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhVien tv = ls.get(position);
                tv.setUserName(edtTaiKhoan.getText().toString());
                tv.setPassWord(edtMatKhau.getText().toString());
                tv.setTenNguoiDung(edtTenNguoiDung.getText().toString());
                dao.updateRow(tv);
                loadData();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void loadData() {
        ls.clear();
        ls = dao.getAll();
        notifyDataSetChanged();
    }
}
