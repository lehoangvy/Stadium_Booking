package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThayDoiSDT extends AppCompatActivity {
    ImageButton btn_back;
    EditText edt_SDT,edt_MatKhau;
    Button btn_Luu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_sdt);
        btn_back = findViewById(R.id.btn_back);
        edt_SDT = findViewById(R.id.edt_MatKhauCu);
        edt_MatKhau = findViewById(R.id.edt_MatKhauMoi);
        btn_Luu = findViewById(R.id.btn_Luu);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newSDT = edt_SDT.getText().toString();
                String matKhau = edt_MatKhau.getText().toString();
                Cursor c = MainActivity.database.rawQuery("select * from NguoiDung where TaiKhoan='huyhihi'",null);
                c.moveToFirst();
                if(matKhau.equals(c.getString(1)) ){
                    Toast.makeText(ThayDoiSDT.this,"Cập nhật thành công",Toast.LENGTH_LONG).show();
                    Cursor c1 = MainActivity.database.rawQuery("update ThongTinNguoidung set SDT='"+ newSDT+"' where TaiKhoan='huyhihi'",null);
                    c1.moveToFirst();
                    c1.close();
                }
                else {
                    Toast.makeText(ThayDoiSDT.this,"Bạn đã nhập sai mật khẩu !!! ",Toast.LENGTH_LONG).show();
                }
                c.close();
            }
        });

    }
}