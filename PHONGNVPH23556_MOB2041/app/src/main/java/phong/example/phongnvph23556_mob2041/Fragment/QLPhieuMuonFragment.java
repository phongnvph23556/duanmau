package phong.example.phongnvph23556_mob2041.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import phong.example.phongnvph23556_mob2041.Adapter.PhieuMuonAdapter;
import phong.example.phongnvph23556_mob2041.Adapter.SachSpinner_Adapter;
import phong.example.phongnvph23556_mob2041.Adapter.ThanhVienSpinner_Adapter;
import phong.example.phongnvph23556_mob2041.DAO.PhieuMuonDAO;
import phong.example.phongnvph23556_mob2041.DAO.SachDAO;
import phong.example.phongnvph23556_mob2041.DAO.ThanhVienDAO;
import phong.example.phongnvph23556_mob2041.Model.PhieuMuon;
import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;
import phong.example.phongnvph23556_mob2041.R;


public class QLPhieuMuonFragment extends Fragment {
    ListView listView;
    ArrayList<PhieuMuon> list;
    static PhieuMuonDAO phieuMuonDAO;
    PhieuMuonAdapter phieuMuonAdapter;
    PhieuMuon item;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");


    FloatingActionButton floatingActionButton;
    Dialog dialog;
    EditText edtmaPM;
    TextView tvNGay, tvTienThue;
    Spinner spinTV, spinmaSach;
    CheckBox checkBoxtrasach;
    Button btnthem, btnhuy;


    ThanhVienSpinner_Adapter thanhVienSpinner_adapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;


    SachSpinner_Adapter sachSpinner_adapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_q_l_phieu_muon, container, false);
        listView = v.findViewById(R.id.lv_qlphieumuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        floatingActionButton = v.findViewById(R.id.btn_floatingPhieuMuon);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        capNhat();

        return v;
    }

    void capNhat() {
        list = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), this, list);
        listView.setAdapter(phieuMuonAdapter);
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieumuon_dialog);
        edtmaPM = dialog.findViewById(R.id.edt_maPM);
        edtmaPM.setEnabled(false);
        spinTV = dialog.findViewById(R.id.spin_maTV);
        spinmaSach = dialog.findViewById(R.id.spin_maSach);
        tvNGay = dialog.findViewById(R.id.tv_ngay);
        //set ngày thuê
        tvNGay.setText("Ngày thuê: " + simpleDateFormat.format(new Date()));

        tvTienThue = dialog.findViewById(R.id.tv_tienthue);
        checkBoxtrasach = dialog.findViewById(R.id.checkbox_trasach);
        btnhuy = dialog.findViewById(R.id.btn_huythemphieumuon);
        btnthem = dialog.findViewById(R.id.btn_themphieumuon);


        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinner_adapter = new ThanhVienSpinner_Adapter(context, listThanhVien);
        spinTV.setAdapter(thanhVienSpinner_adapter);
        spinTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                Toast.makeText(context, "Chọn " + listThanhVien.get(position).getHoTen(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinner_adapter = new SachSpinner_Adapter(context, listSach);
        spinmaSach.setAdapter(sachSpinner_adapter);

        spinmaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Tiền thuê: " + tienThue);
                Toast.makeText(context, "Chọn " + listSach.get(position).getTenSach(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //edit set data lên form
//        if (type != 0) {
//            edtmaPM.setText(String.valueOf(item.getMaPM()));
//            for (int i = 0; i < listThanhVien.size(); i++)
//                if (item.getMaTV() == (listThanhVien.get(i).getMaTV())) {
//                    positionTV = i;
//                }
//            spinTV.setSelection(positionTV);
//
//            for (int i = 0; i < listSach.size(); i++)
//                if (item.getMaSach() == (listSach.get(i).getMaSach())) {
//                    positionSach = i;
//                }
//            spinmaSach.setSelection(positionSach);
//
//            tvNGay.setText("Ngày thuê: " + simpleDateFormat.format(item.getNgay()));
//            tvTienThue.setText("Tiền thuê: " + item.getTienThue());
//            if (item.getTraSach() == 1) {
//                checkBoxtrasach.setChecked(true);
//            } else {
//                checkBoxtrasach.setChecked(false);
//            }
//
//        }

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(new Date());
                item.setTienThue(tienThue);
                if (checkBoxtrasach.isChecked()) {
                    item.setTraSach(1);
                } else {
                    item.setTraSach(0);
                }
                if (type == 0) {
                    //type== 0 thì insert
                    if (phieuMuonDAO.insert(item) > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    //type =1 sẽ update
                    item.setMaPM(Integer.parseInt(edtmaPM.getText().toString()));
                    if (phieuMuonDAO.update(item) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                capNhat();
                dialog.dismiss();
            }
        });


        dialog.show();


    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delele");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phieuMuonDAO.delete(Id);
                capNhat();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        builder.show();
    }

}