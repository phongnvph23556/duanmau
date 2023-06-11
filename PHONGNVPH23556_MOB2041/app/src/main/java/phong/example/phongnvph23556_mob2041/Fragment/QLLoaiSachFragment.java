package phong.example.phongnvph23556_mob2041.Fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import phong.example.phongnvph23556_mob2041.Adapter.LoaiSachAdapter;
import phong.example.phongnvph23556_mob2041.Adapter.SachAdapter;
import phong.example.phongnvph23556_mob2041.DAO.LoaiSachDAO;
import phong.example.phongnvph23556_mob2041.Model.LoaiSach;
import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.R;


public class QLLoaiSachFragment extends Fragment {
    ListView lv_qlloaisach;
    LoaiSachDAO loaiSachDAO;
    LoaiSachAdapter loaiSachAdapter;
    List<LoaiSach> list;
    LoaiSach item;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_q_l_loai_sach, container, false);
        loaiSachDAO=new LoaiSachDAO(getActivity());
        lv_qlloaisach=v.findViewById(R.id.lv_qlloaisach);
        capNhat();

        return v;

    }
    void capNhat() {
        list=loaiSachDAO.getAll();
        loaiSachAdapter = new LoaiSachAdapter(getActivity(),this,list);
        lv_qlloaisach.setAdapter(loaiSachAdapter);
    }

}