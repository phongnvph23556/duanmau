package phong.example.phongnvph23556_mob2041.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import phong.example.phongnvph23556_mob2041.Adapter.LoaiSachSpinerAdapter;
import phong.example.phongnvph23556_mob2041.Adapter.SachAdapter;
import phong.example.phongnvph23556_mob2041.DAO.LoaiSachDAO;
import phong.example.phongnvph23556_mob2041.DAO.SachDAO;
import phong.example.phongnvph23556_mob2041.Model.LoaiSach;
import phong.example.phongnvph23556_mob2041.Model.Sach;
import phong.example.phongnvph23556_mob2041.R;


public class QuanLiSachFragment extends Fragment {
    ListView lvsach;
    SachDAO sachDAO;

    SachAdapter sachAdapter;
    Sach item;
    List<Sach> listsach;

    FloatingActionButton floatingActionButton;
    Dialog dialog;
    EditText edtmasach, edttensach, edtgiathue;
    Spinner spinner;
    Button btnSave, btnCancel;

    LoaiSachSpinerAdapter loaiSachSpinerAdapter;
    ArrayList<LoaiSach> listloaisach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach, position;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quan_li_sach, container, false);
        lvsach = v.findViewById(R.id.lv_qlsach);
        sachDAO = new SachDAO(getActivity());
        capNhat();
        floatingActionButton = v.findViewById(R.id.fl_themsach);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvsach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = listsach.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        return v;

    }

    void capNhat() {
        listsach = (List<Sach>) sachDAO.getAll();
        sachAdapter = new SachAdapter(getActivity(), this, listsach);
        lvsach.setAdapter(sachAdapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.delete(Id);
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
        AlertDialog alertDialog = builder.create();
        builder.show();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edtmasach = dialog.findViewById(R.id.edtmasach);
        edttensach = dialog.findViewById(R.id.edt_tensach);
        edtgiathue = dialog.findViewById(R.id.edt_giathue);
        spinner = dialog.findViewById(R.id.spinner_loaisach);
        btnCancel = dialog.findViewById(R.id.btnhuythemloaisach);
        btnSave = dialog.findViewById(R.id.btnthemsach);

        listloaisach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listloaisach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();

        loaiSachSpinerAdapter = new LoaiSachSpinerAdapter(context, listloaisach);
        spinner.setAdapter(loaiSachSpinerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listloaisach.get(position).getMaLoai();
                Toast.makeText(context, "Chọn" + listloaisach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edtmasach.setEnabled(false);
        if (type != 0) {
            edtmasach.setText(String.valueOf(item.getMaSach()));
            edttensach.setText(item.getTenSach());
            edtgiathue.setText(String.valueOf(item.getGiaThue()));

            for (int i = 0; i < listloaisach.size(); i++)
                if (item.getMaLoai() == (listloaisach.get(i).getMaLoai())) {
                    position = i;
                }
            Log.i("demo", "posSach" + position);
            spinner.setSelection(position);

        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Sach();
                item.setTenSach(edttensach.getText().toString());
                item.setGiaThue(Integer.parseInt(edtgiathue.getText().toString()));
                item.setMaSach(maLoaiSach);

                if (validate() > 0) {
                    if (type == 0) {
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaSach(Integer.parseInt(edtmasach.getText().toString()));
                        if (sachDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                    capNhat();
                    dialog.dismiss();
                }


            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edttensach.getText().length() == 0 || edtgiathue.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}