package phong.example.phongnvph23556_mob2041.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import phong.example.phongnvph23556_mob2041.Fragment.QLThanhVienFragment;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;
import phong.example.phongnvph23556_mob2041.R;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    Context context;
    QLThanhVienFragment fragment;
    ArrayList<ThanhVien> list;

    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView delete;

    public ThanhVienAdapter(@NonNull Context context, QLThanhVienFragment fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanhvien_item,null);

        }
        final ThanhVien item=list.get(position);
        if(item!=null){
            tvMaTV=v.findViewById(R.id.tvMatv);
            tvMaTV.setText("Mã thành viên: "+item.getMaTV());

            tvTenTV=v.findViewById(R.id.tvTentv);
            tvTenTV.setText("Tên thành viên: "+item.getHoTen());

            tvNamSinh=v.findViewById(R.id.tvNamsinhtv);
            tvNamSinh.setText("Năm sinh: "+item.getNamSinh());

            delete=v.findViewById(R.id.imgDelete);




        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaTV()));

            }
        });
        return v;
    }

}
