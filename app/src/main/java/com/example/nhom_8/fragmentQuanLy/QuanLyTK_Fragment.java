package com.example.nhom_8.fragmentQuanLy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Adapter.HoaDonAdapter;
import com.example.nhom_8.Adapter.ThanhVienAdapter;
import com.example.nhom_8.Dao.HoaDonDao;
import com.example.nhom_8.Dao.ThanhVienDao;
import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.HoaDon;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class QuanLyTK_Fragment extends Fragment {
    View view;
    Context context;
    RecyclerView recyclerView;
    ThanhVienDao dao;
    ThanhVienAdapter adapter;
    List<ThanhVien> ls;
    FloatingActionButton button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quan_ly_tk, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.fragment_quan_ly_tk_rcyView);
        button = view.findViewById(R.id.fragment_quan_ly_tk_btnAdd);
        dao = new ThanhVienDao(context);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(Gravity.BOTTOM);
            }
        });
        loadData();

        return view;
    }

    public void loadData() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        dao = new ThanhVienDao(context);
        ls = dao.getAll();
        if (ls.isEmpty()) {

        } else {
            adapter = new ThanhVienAdapter(ls, context);
            recyclerView.setAdapter(adapter);
        }
    }

    public void add(int gravity) {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.add_them_tv, null);
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

        EditText edtTaiKhoan = v.findViewById(R.id.add_them_tv_edt_taiKhoan);
        EditText edtTenNguoiDung = v.findViewById(R.id.add_them_tv_edt_tenNguoiDung);
        EditText edtMatKhau = v.findViewById(R.id.add_them_tv_edt_matKhau);
        Button btnThem = v.findViewById(R.id.add_them_tv_btn_them);
        Button btnHuy = v.findViewById(R.id.add_them_tv_btn_huy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtTaiKhoan.getText().toString();
                String passWord = edtMatKhau.getText().toString();
                String tenNguoiDung = edtTenNguoiDung.getText().toString();
                ThanhVien tv = new ThanhVien();
                tv.setUserName(userName);
                tv.setPassWord(passWord);
                tv.setTenNguoiDung(tenNguoiDung);
                dao.insertRow(tv);
                loadData();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}
