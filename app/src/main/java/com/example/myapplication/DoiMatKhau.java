package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class DoiMatKhau extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_Luu;
    EditText edt_MatKhauCu,edt_MatKhauMoi,edt_NhapLaiMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        btn_back = findViewById(R.id.btn_back);
        btn_Luu = findViewById(R.id.btn_Luu);
        edt_MatKhauCu = findViewById(R.id.edt_MatKhauCu);
        edt_MatKhauMoi = findViewById(R.id.edt_MatKhauMoi);
        edt_NhapLaiMK = findViewById(R.id.edt_NhapLaiMK);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveInfor();
            }
        });
    }
    public  void saveInfor(){
        Cursor c = MainActivity.database.rawQuery("select * from NguoiDung where TaiKhoan='huyhihi'",null);
        c.moveToFirst();
//        Cursor c1 = MainActivity.database.rawQuery("update NguoiDung set MatKhau='123' where TaiKhoan='huyhihi'", null);
//        c1.moveToFirst();
//        c1.close();
        if(edt_MatKhauMoi.getText().toString().equals(edt_NhapLaiMK.getText().toString())){
                if (edt_MatKhauCu.getText().toString().equals(c.getString(1))) {
                    Cursor c1 = MainActivity.database.rawQuery("update NguoiDung set MatKhau='" + edt_MatKhauMoi.getText().toString() + "' where TaiKhoan='huyhihi'", null);
                    c1.moveToFirst();
                    c1.close();
                    Toast.makeText(DoiMatKhau.this,"Cập nhật mật khẩu thành công !!!",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(DoiMatKhau.this,"Nhập sai mật khẩu !!!",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(DoiMatKhau.this,"Nhập lại mật khẩu không trùng khớp !!!",Toast.LENGTH_LONG).show();
        c.close();
    }

}