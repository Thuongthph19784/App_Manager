package com.example.nhom_8.fragmentQuanLy;

import android.app.Dialog;
import android.content.Context;
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

import com.example.nhom_8.Adapter.LoaiDoAnAdapter;
import com.example.nhom_8.Adapter.ThanhVienAdapter;
import com.example.nhom_8.Dao.LoaiDoAnDao;
import com.example.nhom_8.Dao.ThanhVienDao;
import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.LoaiDoAn;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ThemLoaiDoAn_Fragment extends Fragment {
    View view;
    Context context;
    RecyclerView recyclerView;
    LoaiDoAnDao dao;
    LoaiDoAnAdapter adapter;
    List<LoaiDoAn> ls;
    FloatingActionButton button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_them_loai_do_an, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.fragment_loai_do_an_rscView);
        button = view.findViewById(R.id.fragment_loai_do_an_btnAdd);
        loadData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(Gravity.CENTER);
            }
        });
        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayout =new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        dao = new LoaiDoAnDao(context);
        ls = dao.getAll();
        if (ls.isEmpty()){

        }else{
            adapter = new LoaiDoAnAdapter(ls,context);
            recyclerView.setAdapter(adapter);
        }
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
    public void add(int gravity) {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.add_them_loai_do_an, null);
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

        EditText edt = v.findViewById(R.id.add_loai_do_an_edtLoaiDoAn);
        Button btnThem = v.findViewById(R.id.add_loai_do_an_btnThem);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiDoAn loaiDoAn = new LoaiDoAn();
                loaiDoAn.setTenLoaiDoAn(edt.getText().toString());
                dao.insertRow(loaiDoAn);
                loadData();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
