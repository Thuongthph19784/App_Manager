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

import com.example.nhom_8.Adapter.DoAnAdapter;
import com.example.nhom_8.Dao.DoAnDAO;
import com.example.nhom_8.Dao.LoaiDoAnDao;
import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.LoaiDoAn;
import com.example.nhom_8.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ThemDoAn_Fragment extends Fragment {
    View view;
    RecyclerView rcyViewDoAn;
    Context context;
    DoAnAdapter adapter;
    DoAnDAO dao;
    List<DoAn> lsDoAn;
    List<LoaiDoAn> lsLoaiDoAn;
    FloatingActionButton button;
    LoaiDoAnDao loaiDoAnDao;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_them_do_an, container, false);
        rcyViewDoAn = view.findViewById(R.id.fragment_do_an_rscView);
        button = view.findViewById(R.id.fragment_do_an_btnAdd);
        context = view.getContext();
        loaiDoAnDao = new LoaiDoAnDao(context);
        lsLoaiDoAn = loaiDoAnDao.getAll();
        loadData();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(Gravity.CENTER);
            }
        });
        return view;
    }

    public void loadData() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        rcyViewDoAn.setLayoutManager(linearLayout);
        dao = new DoAnDAO(context);
        lsDoAn = dao.getAll();
        if (lsDoAn.isEmpty()) {

        } else {
            adapter = new DoAnAdapter(lsDoAn, context);
            rcyViewDoAn.setAdapter(adapter);
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
        View v = inflater.inflate(R.layout.add_them_do_an, null);
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

        EditText edtLinkAnh = v.findViewById(R.id.add_do_an_edtLinkAnh);
        EditText edtTenDoAN = v.findViewById(R.id.add_do_an_edtTenDoAn);
        EditText edtGiaDoAN = v.findViewById(R.id.add_do_an_edtGiaDoAn);
        Button btnThem = v.findViewById(R.id.add_do_an_btnThem);

        Spinner spinner = v.findViewById(R.id.add_do_an_spinner);
        List<String> lsTenLoaiDoAn = loaiDoAnDao.getAllTenLoaiDoAn();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,lsTenLoaiDoAn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String img = edtLinkAnh.getText().toString();
                String tenDoAn = edtTenDoAN.getText().toString();
                int giaDoAn = Integer.parseInt(edtGiaDoAN.getText().toString());
                int idLoaiDoAn = covertIdLoaiDoAn(spinner.getSelectedItem().toString());
                DoAn doAn = new DoAn();
                doAn.setImage(img);
                doAn.setTenDoAn(tenDoAn);
                doAn.setGia(giaDoAn);
                doAn.setIdLoaiDoAN(idLoaiDoAn);
                dao.insertRow(doAn);
                Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                loadData();
            }
        });

        dialog.show();
    }
    public int covertIdLoaiDoAn(String tenLoaiDoAn){
        for (LoaiDoAn l:lsLoaiDoAn) {
            if (l.getTenDoAn().equals(tenLoaiDoAn)){
                return l.getIdLoaiDoAn();
            }
        }
        return -1;
    }
}
