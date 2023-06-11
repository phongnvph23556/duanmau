package phong.example.phongnvph23556_mob2041.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phong.example.phongnvph23556_mob2041.Database.Data_SQL;
import phong.example.phongnvph23556_mob2041.R;


public class ThemNguoiDungFragment extends Fragment {

    public ThemNguoiDungFragment() {
        // Required empty public constructor
    }

    public static ThemNguoiDungFragment newInstance() {
        ThemNguoiDungFragment fragment = new ThemNguoiDungFragment();

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
        return inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);

    }
}