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

import java.util.List;

import phong.example.phongnvph23556_mob2041.Fragment.QLLoaiSachFragment;
import phong.example.phongnvph23556_mob2041.Model.LoaiSach;
import phong.example.phongnvph23556_mob2041.R;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    QLLoaiSachFragment qlLoaiSachFragment;
    List<LoaiSach> list;
    TextView tv_loaisach;
    ImageView imgDelete;

    public LoaiSachAdapter(@NonNull Context context,  QLLoaiSachFragment qlLoaiSachFragment, List<LoaiSach> list) {
        super(context, 0,  list);
        this.context = context;
        this.qlLoaiSachFragment = qlLoaiSachFragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if(v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_oitem,null);
            final LoaiSach item=list.get(position);

            if(item!=null){
                tv_loaisach=v.findViewById(R.id.tv_tenloai);
                tv_loaisach.setText("Loại sách: "+item.getTenLoai()+"");

                imgDelete=v.findViewById(R.id.img_deleteLsach);
                imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }




        }

        return v;
    }
}
