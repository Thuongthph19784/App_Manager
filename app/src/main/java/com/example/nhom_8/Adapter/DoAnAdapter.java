package com.example.nhom_8.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Dao.DoAnDAO;
import com.example.nhom_8.Dao.HoaDonDao;
import com.example.nhom_8.Dao.LoaiDoAnDao;
import com.example.nhom_8.Dao.YeuThichDao;
import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.HoaDon;
import com.example.nhom_8.Object.LoaiDoAn;
import com.example.nhom_8.R;
import com.example.nhom_8.Object.DoAn;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DoAnAdapter extends RecyclerView.Adapter<DoAnAdapter.ViewHolder>{
    List<DoAn> ls;
    Context context;
    DoAnDAO dao;
    List<LoaiDoAn> lsLoaiDoAn;
    LoaiDoAnDao loaiDoAnDao;


    public DoAnAdapter(List<DoAn> ls, Context context) {
        this.ls = ls;
        this.context = context;
        notifyDataSetChanged();
        loaiDoAnDao = new LoaiDoAnDao(context);
        lsLoaiDoAn = loaiDoAnDao.getAll();
        dao = new DoAnDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_them_do_an,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(CovertBitmap(ls.get(position).getImage()));
        holder.txtTenDoAn.setText(ls.get(position).getTenDoAn());
        holder.txtGiaDoAn.setText(String.valueOf(ls.get(position).getGia()));
        holder.txtTenLoaiDoAn.setText(ls.get(position).getTenLoaiDoAn());
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(Gravity.BOTTOM,holder.getAdapterPosition());
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return ls.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtTenDoAn;
        TextView txtGiaDoAn;
        TextView txtTenLoaiDoAn;
        Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_do_an_img);
            txtTenDoAn = itemView.findViewById(R.id.item_do_an_TxtTenMonAn);
            txtGiaDoAn = itemView.findViewById(R.id.item_do_an_TxtGiaMonAn);
            txtTenLoaiDoAn = itemView.findViewById(R.id.item_do_an_TxtTenLoaiDoAN);
            btnSua = itemView.findViewById(R.id.item_do_an_btnSua);
            btnXoa = itemView.findViewById(R.id.item_do_an_btnDeXoa);
        }
    }
    public Bitmap CovertBitmap(String path) {
        Bitmap mbitmap = null;
        try {
            URL url = new URL(path);
            InputStream inputStream = url.openConnection().getInputStream();

            mbitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mbitmap;
    }
    public void update(int gravity,int position){
        LayoutInflater infl = ((Activity)context).getLayoutInflater();
        View v = infl.inflate(R.layout.update_them_do_an, null);
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

        EditText edtLinkAnh = v.findViewById(R.id.update_do_an_image);
        EditText edtTenDoAN = v.findViewById(R.id.update_do_an_edtTenDoAn);
        EditText edtGiaDoAN = v.findViewById(R.id.update_do_an_edtGia);
        Button btnSua = v.findViewById(R.id.update_do_an_btnSua);
        Spinner spinner = v.findViewById(R.id.update_do_an_spinner);

        edtLinkAnh.setText(ls.get(position).getImage());
        edtTenDoAN.setText(ls.get(position).getTenDoAn());
        edtGiaDoAN.setText(String.valueOf(ls.get(position).getGia()));
        spinner.setSelection(covertIndexSpinnerTenLoaiDoAN(ls.get(position).getIdLoaiDoAN()));
        List<String> lsTenLoaiDoAn = loaiDoAnDao.getAllTenLoaiDoAn();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,lsTenLoaiDoAn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String image = edtLinkAnh.getText().toString();
                String tenDoAn = edtTenDoAN.getText().toString();
                int giaDoAn = Integer.parseInt(edtGiaDoAN.getText().toString());
                int idLoaiDoAn = covertIdLoaiDoAn(spinner.getSelectedItem().toString());

                DoAn doAn = ls.get(position);
                doAn.setImage(image);
                doAn.setTenDoAn(tenDoAn);
                doAn.setGia(giaDoAn);
                doAn.setIdLoaiDoAN(idLoaiDoAn);
                dao.updateRow(doAn);
                loadData();
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void Delete(int position){
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
    public int covertIndexSpinnerTenLoaiDoAN(int idLoaiDoAn){
        int index = 0;
        for (LoaiDoAn l:lsLoaiDoAn) {
            if (l.getIdLoaiDoAn() == idLoaiDoAn){
                return index;
            }
            index++;
        }
        return index;
    }
    public int covertIdLoaiDoAn(String tenLoaiDoAn){
        for (LoaiDoAn l:lsLoaiDoAn) {
            if (l.getTenDoAn().equals(tenLoaiDoAn)){
                return l.getIdLoaiDoAn();
            }
        }
        return -1;
    }
    public void loadData() {
        ls.clear();
        ls = dao.getAll();
        notifyDataSetChanged();
    }

}
