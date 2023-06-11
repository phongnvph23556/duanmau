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

import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;
import phong.example.phongnvph23556_mob2041.R;

public class ThanhVienSpinner_Adapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView maTV,tenTV;
    public ThanhVienSpinner_Adapter(@NonNull Context context, ArrayList<ThanhVien> list) {
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
            v=inflater.inflate(R.layout.thanhvienspinner,null);
        }
        final ThanhVien item=list.get(position);
        if(item!=null){
            maTV=v.findViewById(R.id.tv_maTVspinner);
            maTV.setText(item.getMaTV()+"");
            tenTV=v.findViewById(R.id.tv_tenTVspinner);
            tenTV.setText(item.getHoTen());
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvienspinner,null);
        }
        final ThanhVien item=list.get(position);
        if(item!=null){
            maTV=v.findViewById(R.id.tv_maTVspinner);
            maTV.setText(item.getMaTV()+"");
            tenTV=v.findViewById(R.id.tv_tenTVspinner);
            tenTV.setText(item.getHoTen());
        }

        return v;
    }
}
