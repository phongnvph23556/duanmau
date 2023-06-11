package phong.example.phongnvph23556_mob2041;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import phong.example.phongnvph23556_mob2041.DAO.ThuThuDAO;
import phong.example.phongnvph23556_mob2041.Fragment.DoiMatKhauFragment;

public class DangNhap extends AppCompatActivity {
    EditText edtpass,edtuser;
    Button btnLogin,btnHuy;
    CheckBox remember;
    ThuThuDAO thuThuDAO;
    String stUser,stPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtpass=findViewById(R.id.edt_pass);
        edtuser=findViewById(R.id.edt_tendangnhap);
        btnLogin=findViewById(R.id.btnLogin);
        btnHuy=findViewById(R.id.btnHuy);
        remember=findViewById(R.id.chk_luupass);
        thuThuDAO=new ThuThuDAO(this);


        //đọc user,pass trong sharedpreference
        SharedPreferences sharedPreferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user=sharedPreferences.getString("USERNAME","");
        String pass=sharedPreferences.getString("PASSWORD","");
        Boolean rememb=sharedPreferences.getBoolean("REMEMBER",false);

        edtuser.setText(user);
        edtpass.setText(pass);
        remember.setChecked(rememb);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              edtuser.setText("");
              edtpass.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences preferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(!status){
            //xóa tình trạng lưu trước đó
            editor.clear();
        }else {
            //lưu dữ liệu
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        //lưu lại toàn bộ dữ liệu
        editor.commit();

    }
    public void checkLogin(){
        stUser=edtuser.getText().toString();
        stPass=edtpass.getText().toString();
        if(TextUtils.isEmpty(stUser)||stPass.trim().equals("")){
            edtuser.setError("Tên đăng nhập không được để trống");
            return;

        }else {
            if(thuThuDAO.checkLogin(stUser,stPass)>0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                rememberUser(stUser,stPass,remember.isChecked());
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",stUser);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng",Toast.LENGTH_SHORT).show();
            }
        }
    }
}