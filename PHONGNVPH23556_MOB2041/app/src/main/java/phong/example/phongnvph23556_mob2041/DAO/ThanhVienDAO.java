package phong.example.phongnvph23556_mob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phong.example.phongnvph23556_mob2041.DangNhap;
import phong.example.phongnvph23556_mob2041.Database.SQLite;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;

public class ThanhVienDAO {
    private SQLiteDatabase db;

    public ThanhVienDAO(Context context) {
        SQLite sqLite=new SQLite(context);
        db=sqLite.getWritableDatabase();
    }
    public long insert(ThanhVien obj){
        ContentValues values=new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        return  db.insert("ThanhVien",null,values);
    }

    public int update(ThanhVien obj){
        ContentValues values=new ContentValues();
        values.put("hoTen",obj.getHoTen());
        values.put("namSinh",obj.getNamSinh());
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(obj.getMaTV())});

    }
    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien obj=new ThanhVien();
            obj.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            obj.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            obj.setNamSinh(c.getString(c.getColumnIndex("namSinh")));

            list.add(obj);
        }
        return list;
    }

    //get data
    public List<ThanhVien> getAll(){
        String sql="SELECT * FROM ThanhVien";
        return getData(sql);
    }

    //get data theo id
    public ThanhVien getID(String id){
        String sql="SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list=getData(sql,id);
        if(list!=null){
            return list.get(0);
        }
        return null;
    }


}
