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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import phong.example.phongnvph23556_mob2041.Adapter.ThanhVienAdapter;
import phong.example.phongnvph23556_mob2041.DAO.ThanhVienDAO;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;
import phong.example.phongnvph23556_mob2041.R;


public class QLThanhVienFragment extends Fragment {

    ListView lvQLthanhvien;
    ArrayList<ThanhVien> list;
    static ThanhVienDAO thanhVienDAO;
    ThanhVienAdapter adapter;
    ThanhVien item;
    FloatingActionButton floatingActionButton;
    Dialog dialog;
    EditText edtMaTV, edtTenTV, edtNamsinhTV;
    Button btnSave, btnHuy;


    public static QLThanhVienFragment newInstance() {
        QLThanhVienFragment fragment = new QLThanhVienFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_q_l_thanh_vien, container, false);
        lvQLthanhvien = v.findViewById(R.id.lv_qlthanhvien);
        floatingActionButton = v.findViewById(R.id.fl_themthanhvien);
        thanhVienDAO = new ThanhVienDAO(getActivity());

        capNhatlv();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvQLthanhvien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getContext(), 1);
                btnSave.setText("Cập Nhật");
                return false;
            }
        });


        return v;
    }

    public void capNhatlv() {
        list = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        adapter = new ThanhVienAdapter(getActivity(), this, list);
        lvQLthanhvien.setAdapter(adapter);
    }


    //hàm xóa
    public void xoa(final String Id) {
        //Sử dụng Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Gọi function Delete
                thanhVienDAO.delete(Id);

                //cập nhật lại lv;
                capNhatlv();
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
        dialog.setContentView(R.layout.themthanhvien_dialog);

        edtMaTV = dialog.findViewById(R.id.edtMaTV);
        edtTenTV = dialog.findViewById(R.id.edtTenTV);
        edtNamsinhTV = dialog.findViewById(R.id.edtNamsinhTV);
        btnHuy = dialog.findViewById(R.id.btnhuythemtv);
        btnSave = dialog.findViewById(R.id.btnthemtv);

        edtMaTV.setEnabled(false);
        if (type != 0) {
            edtMaTV.setText(String.valueOf(item.getMaTV()));
            edtTenTV.setText(item.getHoTen());
            edtNamsinhTV.setText(item.getNamSinh());

        }


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTenTV.setText("");
                edtNamsinhTV.setText("");
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThanhVien();
                item.setHoTen(edtTenTV.getText().toString());
                item.setNamSinh(edtNamsinhTV.getText().toString());
                if (validate() > 0) {
                    //type=0(insert)
                    if (type == 0) {

                        if (thanhVienDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        item.setMaTV(Integer.parseInt(edtMaTV.getText().toString()));
                        if (thanhVienDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                capNhatlv();
                dialog.dismiss();

            }
        });
        dialog.show();


    }

    public int validate() {
        int check = 1;
        if (edtTenTV.getText().length() == 0 || edtNamsinhTV.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}