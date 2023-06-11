package phong.example.phongnvph23556_mob2041.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import phong.example.phongnvph23556_mob2041.DAO.SachDAO;
import phong.example.phongnvph23556_mob2041.DAO.ThanhVienDAO;
import phong.example.phongnvph23556_mob2041.Fragment.QLPhieuMuonFragment;
import phong.example.phongnvph23556_mob2041.Model.PhieuMuon;
import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;
import phong.example.phongnvph23556_mob2041.R;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    private Context context;
    QLPhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvmaPM, tvTenPM, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgdelete;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

    public PhieuMuonAdapter(@NonNull Context context,QLPhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
        super(context, 0,list);
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
            v=inflater.inflate(R.layout.phieumuon_item,null);

        }
        final PhieuMuon item=list.get(position);
        if(item!=null){
            tvmaPM=v.findViewById(R.id.tv_maPM);
            tvmaPM.setText("Mã phiếu: "+item.getMaPM());

            sachDAO =new SachDAO(context);
            Sach sach=sachDAO.getID(String.valueOf(item.getMaSach()));

            tvTenSach=v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());
            thanhVienDAO=new ThanhVienDAO(context);
            ThanhVien thanhVien=thanhVienDAO.getID(String.valueOf(item.getMaTV()));
            tvTenPM=v.findViewById(R.id.tv_tenTV);
            tvTenPM.setText("Thành viên: "+thanhVien.getHoTen());
            tvTienThue=v.findViewById(R.id.tv_tienThue);
            tvTienThue.setText("Tiền thuê: "+item.getTienThue());
            tvNgay=v.findViewById(R.id.tv_tvNgayPm);

            tvNgay.setText("Ngày thuê: "+item.getNgay());
            tvTraSach=v.findViewById(R.id.tvTraSach);

            if(item.getTraSach()==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgdelete=v.findViewById(R.id.img_deletePM);



        }
        imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });

        return v;
    }
}
