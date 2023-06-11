package phong.example.phongnvph23556_mob2041.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import phong.example.phongnvph23556_mob2041.Fragment.Top10Fragment;
import phong.example.phongnvph23556_mob2041.Model.Top;
import phong.example.phongnvph23556_mob2041.R;

public class Top10Adapter extends ArrayAdapter<Top> {
    private Context context;
    Top10Fragment fragment;
    ArrayList<Top> list;
    TextView tvSach,tvSoluong;
    ImageView imgdelete;

    public Top10Adapter(@NonNull Context context, Top10Fragment fragment, ArrayList<Top> list) {
        super(context, 0, list);
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
            v=inflater.inflate(R.layout.top10item,null);

        }
        final Top item=list.get(position);
        if(item!=null){
            tvSach=v.findViewById(R.id.tv_Sach);
            tvSach.setText("Sách: "+item.getTenSach());

            tvSoluong=v.findViewById(R.id.tv_Soluong);
            tvSoluong.setText("Số lượng: "+item.getSoLuong());
        }
        return v;
    }
}
