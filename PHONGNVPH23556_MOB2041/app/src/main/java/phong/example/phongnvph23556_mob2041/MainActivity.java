package phong.example.phongnvph23556_mob2041;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import phong.example.phongnvph23556_mob2041.DAO.ThuThuDAO;
import phong.example.phongnvph23556_mob2041.Database.SQLite;
import phong.example.phongnvph23556_mob2041.Fragment.DoanhThuFragment;
import phong.example.phongnvph23556_mob2041.Fragment.DoiMatKhauFragment;
import phong.example.phongnvph23556_mob2041.Fragment.QLLoaiSachFragment;
import phong.example.phongnvph23556_mob2041.Fragment.QLPhieuMuonFragment;
import phong.example.phongnvph23556_mob2041.Fragment.QLThanhVienFragment;
import phong.example.phongnvph23556_mob2041.Fragment.QuanLiSachFragment;
import phong.example.phongnvph23556_mob2041.Fragment.ThemNguoiDungFragment;
import phong.example.phongnvph23556_mob2041.Fragment.Top10Fragment;
import phong.example.phongnvph23556_mob2041.Model.ThuThu;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView  txtUser;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ThuThuDAO thuThuDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLite sqLite=new SQLite(getApplicationContext());

        setTitle("DỰ ÁN MẪU MOB2041");
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawrLayout);
        imageView = findViewById(R.id.imgMenu);
        navigationView = findViewById(R.id.navigationView);
        txtUser=findViewById(R.id.txtUser);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


//        //hiện user lên header
//        Intent intent=getIntent();
//        String user=intent.getStringExtra("user");
//        thuThuDAO=new ThuThuDAO(this);
//        ThuThu thuThu=thuThuDAO.getID(user);
//        String username=thuThu.getHoTen();
//        txtUser.setText("Xin chào "+username);

        Fragment fragment;
        Class fragmentClass = null;
        fragmentClass = QLPhieuMuonFragment.class;
        try {
            fragment =(Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

            drawerLayout.closeDrawer(GravityCompat.START);

        } catch (Exception exception) {

        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                Class fragmentClass = null;
                switch (item.getItemId()) {
                    case R.id.nv_phieumuon:
                        fragmentClass = QLPhieuMuonFragment.class;
                        break;
                    case R.id.nv_qlloaisach:
                        fragmentClass= QLLoaiSachFragment.class;
                        break;
                    case R.id.nv_qlsach:
                        fragmentClass= QuanLiSachFragment.class;
                        break;
                    case R.id.nv_thanhvien:
                        fragmentClass= QLThanhVienFragment.class;
                        break;
                    case R.id.sub_top10:
                        fragmentClass= Top10Fragment.class;
                        break;
                    case R.id.sub_doanhthu:
                        fragmentClass= DoanhThuFragment.class;
                        break;
                    case R.id.themNguoiDung:
                        fragmentClass= ThemNguoiDungFragment.class;
                        break;
                    case R.id.doiPass:
                        fragmentClass=DoiMatKhauFragment.class;
                        break;
                    case R.id.dangXuat:
                        startActivity(new Intent(getApplicationContext(), DangNhap.class));
                        finish();
                        break;
                        
                }
                try {
                    fragment =(Fragment) fragmentClass.newInstance();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit();

                    drawerLayout.closeDrawer(GravityCompat.START);

                } catch (Exception exception) {

                }
                return false;
            }
        });

    }
}