package phong.example.phongnvph23556_mob2041.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import phong.example.phongnvph23556_mob2041.Model.LoaiSach;
import phong.example.phongnvph23556_mob2041.R;

public class LoaiSachSpinerAdapter extends ArrayAdapter<LoaiSach> {
    Context context;
    ArrayList<LoaiSach> list;
    TextView tvmaLoaiSach,tvTenLoaiSach;

    public LoaiSachSpinerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spiner,null);


        }
        final  LoaiSach item=list.get(position);
        if(item!=null){
            tvmaLoaiSach=v.findViewById(R.id.tv_maloaisachspin);
            tvmaLoaiSach.setText(item.getMaLoai()+"");

            tvTenLoaiSach=v.findViewById(R.id.tv_tenloaisachspin);
            tvTenLoaiSach.setText(item.getTenLoai());

        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sach_item_spiner,null);

        }
        final  LoaiSach item=list.get(position);
        if(item!=null){
            tvmaLoaiSach=v.findViewById(R.id.tv_maloaisachspin);
            tvmaLoaiSach.setText(item.getMaLoai()+"");

            tvTenLoaiSach=v.findViewById(R.id.tv_tenloaisachspin);
            tvTenLoaiSach.setText(item.getTenLoai());
        }


        return v;
    }
}
