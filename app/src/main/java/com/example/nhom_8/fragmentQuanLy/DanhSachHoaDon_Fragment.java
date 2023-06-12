package com.example.nhom_8.fragmentQuanLy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Adapter.HoaDonAdapter;
import com.example.nhom_8.Dao.HoaDonDao;
import com.example.nhom_8.Object.HoaDon;
import com.example.nhom_8.R;

import java.util.List;

public class DanhSachHoaDon_Fragment extends Fragment {
    View view;
    Context context;
    RecyclerView recyclerView;
    HoaDonDao dao;
    HoaDonAdapter adapter;
    List<HoaDon> ls;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danh_sach_hoa_don, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.fragment_hoa_don_rcv);
        loadData();


        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayout =new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        dao = new HoaDonDao(context);
        ls = dao.getAll();
        if (ls.isEmpty()){

        }else{
            adapter = new HoaDonAdapter(ls,context);
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
}
