package phong.example.phongnvph23556_mob2041.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import phong.example.phongnvph23556_mob2041.Database.SQLite;
import phong.example.phongnvph23556_mob2041.Model.Top;

public class Top10DAO {

    SQLiteDatabase db;
    public Top10DAO(Context context) {
        SQLite dbHelper = new SQLite(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Top> selectAll() {
        String sql = "SELECT Sach.tensach, Sach.giathue, COUNT(PhieuMuon.masach) FROM Sach " +
                "LEFT OUTER JOIN PhieuMuon ON Sach.masach = PhieuMuon.masach " +
                "GROUP BY PhieuMuon.masach " +                                                  // gộp mã sách trong phiếu mượn
                "ORDER BY COUNT(PhieuMuon.masach) DESC LIMIT 10";                                       // Sắp xếp giảm dần của số lượng mã sách đã mượn
        List<Top> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tenSach = cursor.getString(0);
            int tienThue = cursor.getInt(1);
            int soLuong = cursor.getInt(2);
//            list.add(new Top(tenSach, tienThue, soLuong));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "SELECT SUM(tienthue) FROM PhieuMuon WHERE ngay BETWEEN'" + tuNgay + "' AND '" + denNgay + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int doanhThu = cursor.getInt(0);
        cursor.close();
        return doanhThu;
    }

}
