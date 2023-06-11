package phong.example.phongnvph23556_mob2041.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phong.example.phongnvph23556_mob2041.Database.SQLite;
import phong.example.phongnvph23556_mob2041.Model.LoaiSach;
import phong.example.phongnvph23556_mob2041.Model.ThanhVien;

public class LoaiSachDAO {
    private SQLiteDatabase db;

    public LoaiSachDAO(Context context) {
        SQLite sqLite=new SQLite(context);
        db=sqLite.getWritableDatabase();
    }
    public long insert(LoaiSach obj){
        ContentValues values=new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        return  db.insert("LoaiSach",null,values);
    }

    public int update(LoaiSach obj){
        ContentValues values=new ContentValues();
        values.put("tenLoai",obj.getTenLoai());
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(obj.getMaLoai())});

    }
    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list=new ArrayList<>();
        Cursor c=db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            LoaiSach obj=new LoaiSach();
            obj.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            obj.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));

            list.add(obj);
        }
        return list;
    }

    //get data
    public List<LoaiSach> getAll(){
        String sql="SELECT * FROM LoaiSach";
        return getData(sql);
    }

    //get data theo id
    public LoaiSach getID(String id){
        String sql="SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list=getData(sql,id);
        if(list!=null){
            return list.get(0);
        }
      return null;

    }


}
