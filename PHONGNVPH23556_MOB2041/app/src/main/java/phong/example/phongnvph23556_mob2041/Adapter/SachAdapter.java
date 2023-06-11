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

import phong.example.phongnvph23556_mob2041.DAO.LoaiSachDAO;
import phong.example.phongnvph23556_mob2041.Fragment.QuanLiSachFragment;
import phong.example.phongnvph23556_mob2041.Model.LoaiSach;
import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.R;

public class SachAdapter extends ArrayAdapter<Sach> {
    Context context;
    QuanLiSachFragment quanLiSachFragment;
    List<Sach> list;
    TextView tvmasach,tvtensach,tvgiathue,tvloaisach;
    ImageView deletesach;

    public SachAdapter(@NonNull Context context, QuanLiSachFragment quanLiSachFragment, List<Sach> list) {
        super(context, 0,list);
        this.context = context;
        this.quanLiSachFragment = quanLiSachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.qlsach_item,null);
            final  Sach item=list.get(position);

            if(item!=null){
                LoaiSachDAO loaiSachDAO=new LoaiSachDAO(context);
//                LoaiSach loaiSach=loaiSachDAO.getID(item.getMaLoai()+"");

                tvmasach=v.findViewById(R.id.tv_masach);
                tvmasach.setText("Mã sách: "+item.getMaSach());

                tvtensach=v.findViewById(R.id.tv_tensach);
                tvtensach.setText("Tên sách: "+item.getTenSach());

                tvgiathue=v.findViewById(R.id.tv_giathue);
                tvgiathue.setText("Gía thuê: "+item.getGiaThue());

                tvloaisach=v.findViewById(R.id.tv_loaisach);
//                tvloaisach.setText("Loại sách: "+loaiSach.getTenLoai());

                deletesach=v.findViewById(R.id.qlsach_delete);
                deletesach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       quanLiSachFragment.xoa(String.valueOf(item.getMaSach()));
                    }
                });
            }

        }

        return v;

    }
}
