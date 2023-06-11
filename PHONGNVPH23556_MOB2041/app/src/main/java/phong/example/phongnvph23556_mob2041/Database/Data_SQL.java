package phong.example.phongnvph23556_mob2041.Database;

import android.database.sqlite.SQLiteDatabase;

public class Data_SQL {

    public static final String INSERT_THUTHU="Insert into ThuThu(maTT, hoTen, matKhau) values" +
            "('admin','p admin','123'),('phong','phong ca','999')";

   public static final String Inser_loaisach="INSERT INTO LoaiSach(tenLoai) VALUES('công nghệ'),('làm giàu'),('marketing')";

   public static final String INSERT_PHIEUMUON="INSERT INTO PhieuMuon(maTT,maTV,maSach,tienThue,ngay,traSach) VALUES('admin','1','1','9999','10/05/2003',0)";

   public static final String INSERT_SACH=" INSERT INTO Sach(tenSach,giaThue,maLoai) VALUES('Sách Đạo Đức','111','1'),('Sách Phật Gíao','342','2')";

    public static final String INSERT_THANHVIEN = "Insert into ThanhVien(hoTen,namSinh) values" +
            "('Nguyen van tu','1009')," +
            "('Nguyen van quan','1954')," +
            "('Nguyen van thang','1985')," +
            "('Nguyen van duong','2005')," +
            "('Nguyen van tien','2008')";
}
