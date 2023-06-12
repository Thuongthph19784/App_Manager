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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Dao.LoaiDoAnDao;
import com.example.nhom_8.Object.DoAn;
import com.example.nhom_8.Object.LoaiDoAn;
import com.example.nhom_8.Object.ThanhVien;
import com.example.nhom_8.R;

import java.util.List;

public class LoaiDoAnAdapter extends RecyclerView.Adapter<LoaiDoAnAdapter.ViewHolder> {
    List<LoaiDoAn> ls;
    Context context;
    LayoutInflater inflater;
    LoaiDoAnDao loaiDoAnDao;

    public LoaiDoAnAdapter(List<LoaiDoAn> ls, Context context) {
        this.ls = ls;
        this.context = context;
        loaiDoAnDao = new LoaiDoAnDao(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_them_loai_do_an,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenLoaiDoAn.setText(ls.get(position).getTenDoAn());
        holder.btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(Gravity.BOTTOM,holder.getAdapterPosition());
            }
        });
        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenLoaiDoAn;
        Button btnSua,btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenLoaiDoAn = itemView.findViewById(R.id.item_loai_do_an_TxtTenLoai);
            btnSua = itemView.findViewById(R.id.item_loai_do_an_btnSua);
            btnXoa = itemView.findViewById(R.id.item_loai_do_an_btnXoa);
        }
    }
    public void update(int gravity,int position){
        LayoutInflater infl = ((Activity)context).getLayoutInflater();
        View v = infl.inflate(R.layout.update_them_loai_do_an, null);
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

        EditText edtLoaiDoAn = v.findViewById(R.id.update_loai_do_an_edtLoaiDoAn);
        Button btnSuaLoaiDoAn = v.findViewById(R.id.update_loai_do_an_btnSua);
        edtLoaiDoAn.setText(ls.get(position).getTenDoAn());

        btnSuaLoaiDoAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiDoAn loaiDoAn = ls.get(position);
                loaiDoAn.setTenLoaiDoAn(edtLoaiDoAn.getText().toString());
                loaiDoAnDao.updateRow(loaiDoAn);
                loadData();
                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        dialog.show();
    }
    public void loadData() {
        ls.clear();
        ls = loaiDoAnDao.getAll();
        notifyDataSetChanged();
    }
    public void delete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa");
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loaiDoAnDao.deleteRow(ls.get(position));
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
}
