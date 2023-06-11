package phong.example.phongnvph23556_mob2041.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import phong.example.phongnvph23556_mob2041.DAO.ThuThuDAO;
import phong.example.phongnvph23556_mob2041.Model.ThuThu;
import phong.example.phongnvph23556_mob2041.R;


public class DoiMatKhauFragment extends Fragment {
    TextInputEditText edt_matkhaucu,edt_matkhaumoi,edt_nhaplaimatkhaumoi;
    Button btn_luudoimk,btn_huydoimk;
    ThuThuDAO thuThuDAO;



    public static DoiMatKhauFragment newInstance() {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();

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
        View v = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        edt_matkhaucu=v.findViewById(R.id.matkhaucu);
        edt_matkhaumoi=v.findViewById(R.id.matkhaumoi);
        edt_nhaplaimatkhaumoi=v.findViewById(R.id.matkhaumoi2);
        btn_luudoimk=v.findViewById(R.id.luudoimk);
        btn_huydoimk=v.findViewById(R.id.huydoimk);
        thuThuDAO=new ThuThuDAO(getActivity());

        btn_luudoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              SharedPreferences preferences=getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
              String user=preferences.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu=thuThuDAO.getID(user);
                    thuThu.setMatKhau(edt_matkhaumoi.getText().toString());
                    if(thuThuDAO.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        edt_matkhaucu.setText("");
                        edt_matkhaumoi.setText("");
                        edt_nhaplaimatkhaumoi.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thất bại",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        btn_huydoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_matkhaucu.setText("");
                edt_matkhaumoi.setText("");
                edt_nhaplaimatkhaumoi.setText("");
            }
        });

        return v;
    }

    public  int validate(){
        int check=1;
        if(edt_matkhaucu.getText().length()==0||edt_matkhaumoi.getText().length()==0||edt_nhaplaimatkhaumoi.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            //đọc user,pass trong SharePreferences
            SharedPreferences preferences=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String matkhaucu=preferences.getString("PASSWORD","");
            String matkhaumoi=edt_matkhaumoi.getText().toString();
            String matkhaumoi2=edt_nhaplaimatkhaumoi.getText().toString();
            if(!matkhaucu.equals(edt_matkhaucu.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check=-1;

            }
            if(!matkhaumoi.equals(matkhaumoi2)){
                Toast.makeText(getContext(),"Mật khẩu mới không trùng khớp",Toast.LENGTH_SHORT).show();
                check=-1;

            }
        }
        return check;
    }
}