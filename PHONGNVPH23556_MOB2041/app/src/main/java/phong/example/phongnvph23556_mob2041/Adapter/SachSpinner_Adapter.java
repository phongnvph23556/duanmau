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
import java.util.List;

import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.R;

public class SachSpinner_Adapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
   TextView tvmaSach,tenSach;
    public SachSpinner_Adapter(@NonNull Context context, ArrayList<Sach> list) {
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
            v=inflater.inflate(R.layout.sachspinner,null);
        }
        final Sach item=list.get(position);
        if(item!=null){
            tvmaSach=v.findViewById(R.id.tv_maSachspinner);
            tvmaSach.setText(item.getMaSach()+"");
            tenSach=v.findViewById(R.id.tv_tenSachspinner);
            tenSach.setText(item.getTenSach()+"");
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.sachspinner,null);
        }
        final Sach item=list.get(position);
        if(item!=null){
            tvmaSach=v.findViewById(R.id.tv_maSachspinner);
            tvmaSach.setText(item.getMaSach()+"");
            tenSach=v.findViewById(R.id.tv_tenSachspinner);
            tenSach.setText(item.getTenSach());
        }
        return v;
    }
}

