package phong.example.phongnvph23556_mob2041.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import phong.example.phongnvph23556_mob2041.Adapter.Top10Adapter;
import phong.example.phongnvph23556_mob2041.DAO.PhieuMuonDAO;
import phong.example.phongnvph23556_mob2041.Model.PhieuMuon;
import phong.example.phongnvph23556_mob2041.Model.Top;
import phong.example.phongnvph23556_mob2041.R;


public class Top10Fragment extends Fragment {
    ListView lvtop10;
    ArrayList<Top> list;
    Top10Adapter top10Adapter;

    public Top10Fragment() {
        // Required empty public constructor
    }

    public static Top10Fragment newInstance() {
        Top10Fragment fragment = new Top10Fragment();

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
        View v= inflater.inflate(R.layout.fragment_top10, container, false);
        lvtop10=v.findViewById(R.id.lv_top10);
        PhieuMuonDAO phieuMuonDAO=new PhieuMuonDAO(getActivity());
        list= (ArrayList<Top>) phieuMuonDAO.getTop();
        top10Adapter=new Top10Adapter(getActivity(),this,list);
        lvtop10.setAdapter(top10Adapter);

        return v;
    }
}