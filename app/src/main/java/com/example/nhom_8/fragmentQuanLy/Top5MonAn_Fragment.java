package com.example.nhom_8.fragmentQuanLy;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.nhom_8.Dao.ThongKeDao;
import com.example.nhom_8.Object.Top;
import com.example.nhom_8.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Top5MonAn_Fragment extends Fragment {
    View view;
    PieChart pieChart;
    ThongKeDao dao;
    Top top;
    List<Top> lsTop;
    List<PieEntry> ls;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_mon_an_ban_chay, container, false);
        pieChart = view.findViewById(R.id.pieChart);
        dao = new ThongKeDao();
        lsTop = dao.getAllTop();
        getPieChart();
        return view;
    }
    public void getPieChart(){
        ls = new ArrayList<>();
        for (Top top:lsTop) {
            DecimalFormat format = new DecimalFormat("0.#");
            int value = top.getSoLanBanDuoc();
            ls.add(new PieEntry(value,top.getTenDoAn()));
        }

        PieDataSet pieDataSet =new PieDataSet(ls,"Top 5 món ăn bán chạy nhất");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Top 5 món ăn bán chạy nhất");
        pieChart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        pieChart.animate();
    }

}
