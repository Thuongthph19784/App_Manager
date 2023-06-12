package com.example.nhom_8.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom_8.R;
import com.example.nhom_8.fragmentQuanLy.DanhSachHoaDon_Fragment;
import com.example.nhom_8.fragmentQuanLy.QuanLyTK_Fragment;
import com.example.nhom_8.fragmentQuanLy.ThemDoAn_Fragment;
import com.example.nhom_8.fragmentQuanLy.ThemLoaiDoAn_Fragment;
import com.example.nhom_8.fragmentQuanLy.TongDoanhThu_Fragment;
import com.example.nhom_8.fragmentQuanLy.Top5MonAn_Fragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView view;
    Intent intent;
    Context context = this;
    final String[] s = new String[]{
            "Quản lý tài khoản",
            "Thêm loại đồ ăn",
            "Thêm đồ ăn",
            "Tổng doanh thu",
            "Top 5 món ăn bán chạy",
            "Danh sách hóa đơn"
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(s[2]);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDRW, R.string.closeDRW);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        view = findViewById(R.id.navigationView);
        view.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        replaceFragment(new ThemDoAn_Fragment());
        view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(true);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_nav_qlTK) {
            replaceFragment(new QuanLyTK_Fragment());

            initToolBar(s[0]);
            view.getMenu().findItem(R.id.menu_nav_qlTK).setChecked(true);
            view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dSHD).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dangXuat).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_tongDoanhThu).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_topMABC).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tLDA).setChecked(false);
        } else if (item.getItemId() == R.id.menu_nav_tLDA) {
            replaceFragment(new ThemLoaiDoAn_Fragment());

            initToolBar(s[1]);
            view.getMenu().findItem(R.id.menu_nav_qlTK).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tLDA).setChecked(true);
            view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_tongDoanhThu).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_topMABC).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dSHD).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dangXuat).setChecked(false);
        } else if (item.getItemId() == R.id.menu_nav_tDA) {
            replaceFragment(new ThemDoAn_Fragment());

            initToolBar(s[2]);
            view.getMenu().findItem(R.id.menu_nav_qlTK).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tLDA).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(true);
//            view.getMenu().findItem(R.id.menu_nav_tongDoanhThu).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_topMABC).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dSHD).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dangXuat).setChecked(false);
        }
//        else if (item.getItemId() == R.id.menu_nav_tongDoanhThu) {
//            replaceFragment(new TongDoanhThu_Fragment());
//
//            initToolBar(s[3]);
//            view.getMenu().findItem(R.id.menu_nav_qlTK).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_tLDA).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(false);
////            view.getMenu().findItem(R.id.menu_nav_tongDoanhThu).setChecked(true);
//            view.getMenu().findItem(R.id.menu_nav_topMABC).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_dSHD).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_dangXuat).setChecked(false);
//        }
        else if (item.getItemId() == R.id.menu_nav_topMABC) {
            replaceFragment(new Top5MonAn_Fragment());

            initToolBar(s[4]);
            view.getMenu().findItem(R.id.menu_nav_qlTK).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tLDA).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_tongDoanhThu).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_topMABC).setChecked(true);
            view.getMenu().findItem(R.id.menu_nav_dSHD).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dangXuat).setChecked(false);


        } else if (item.getItemId() == R.id.menu_nav_dSHD) {
            replaceFragment(new DanhSachHoaDon_Fragment());

            initToolBar(s[5]);
            view.getMenu().findItem(R.id.menu_nav_qlTK).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tLDA).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_tDA).setChecked(false);
//            view.getMenu().findItem(R.id.menu_nav_tongDoanhThu).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_topMABC).setChecked(false);
            view.getMenu().findItem(R.id.menu_nav_dSHD).setChecked(true);
            view.getMenu().findItem(R.id.menu_nav_dangXuat).setChecked(false);

        }
//        else if (item.getItemId() == R.id.menu_nav_logout) {
//            intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//        }
        else{
            super.onBackPressed();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void initToolBar(String s) {
        toolbar.setTitle(s);
    }
}
