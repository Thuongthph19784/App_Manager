package com.example.nhom_8.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom_8.Dao.HoaDonDao;
import com.example.nhom_8.Object.HoaDon;
import com.example.nhom_8.R;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{
    List<HoaDon> ls;
    Context context;
    LayoutInflater inflater;
    HoaDonDao dao;
    public HoaDonAdapter(List<HoaDon> ls, Context context) {
        this.ls = ls;
        this.context = context;
        dao = new HoaDonDao(context);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenMonAN.setText(ls.get(position).getTenMonAn());
        holder.txtGia.setText(String.valueOf(ls.get(position).getGiaMonAn()));
        holder.txtSoLuong.setText(String.valueOf(ls.get(position).getSoLuong()));
        holder.txtTongGia.setText(String.valueOf(ls.get(position).getTongGia()));
        holder.txtNgayMua.setText(ls.get(position).getNgayMua());
        holder.txtNguoiDung.setText(ls.get(position).getTenThanhVien());
        holder.button.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenMonAN,txtGia,txtSoLuong,txtTongGia,txtNgayMua,txtNguoiDung;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonAN = itemView.findViewById(R.id.item_hoa_don_ten_mon_an);
            txtGia = itemView.findViewById(R.id.item_hoa_don_gia_mon_an);
            txtSoLuong = itemView.findViewById(R.id.item_hoa_don_soLuong);
            txtTongGia = itemView.findViewById(R.id.item_hoa_don_tong_gia);
            txtNgayMua = itemView.findViewById(R.id.item_hoa_don_ngay_mua);
            txtNguoiDung = itemView.findViewById(R.id.item_hoa_don_ten_nguoi_mua);
            button = itemView.findViewById(R.id.item_hoa_don_btnDeXoa);
        }
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
    public void loadData() {
        ls.clear();
        ls = dao.getAll();
        notifyDataSetChanged();
    }
}
