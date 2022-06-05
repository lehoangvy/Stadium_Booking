package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ThayDoiThongTinCaNhan extends AppCompatActivity {
    Spinner spinner;
    ImageButton btn_back;
    Button btn_Luu;
    EditText edt_Ten,edt_NgaySinh,edt_Email;
    Spinner sp_GioiTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_doi_thong_tin_ca_nhan);
        Spinner spinner = findViewById(R.id.sp_GioiTinh);
        edt_Ten = findViewById(R.id.edt_Ten);
        edt_Email = findViewById(R.id.edt_Email);
        edt_NgaySinh = findViewById(R.id.edt_NgaySinh);
        btn_back = findViewById(R.id.btn_back);
        btn_Luu = findViewById(R.id.btn_Luu);
        sp_GioiTinh = findViewById(R.id.sp_GioiTinh);
        String[] items = new String[]{"Nam", "Nữ"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        btn_back = findViewById(R.id.btn_back);

        displayInfor();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateInfor();
                displayInfor();
            }
        });


    }


    public void displayInfor(){
        Cursor c = MainActivity.database.rawQuery("Select * from ThongTinNguoiDung where TaiKhoan='huyhihi'",null);
        c.moveToFirst();
        int gt = 0;
        if(c.getInt(4)==1){
            gt=0;
        }
        else
            gt=1;
        edt_Ten.setText(c.getString(1));
        edt_Email.setText(c.getString(3));
        edt_NgaySinh.setText(c.getString(5));
        sp_GioiTinh.setSelection(gt);
    }

    public void UpdateInfor(){
        int choise = sp_GioiTinh.getSelectedItemPosition();
        int gt=-1;
        if(choise == 0 ){
            gt=1;
        }else
            gt=0;
        try {
            Cursor c = MainActivity.database.rawQuery("update ThongTinNguoiDung set TenNguoiDung='" + edt_Ten.getText().toString() + "',Email='" + edt_Email.getText().toString() + "',NgaySinh='" + edt_NgaySinh.getText().toString() + "',GioiTinh=" + gt + " where TaiKhoan='huyhihi'", null);
            c.moveToFirst();
            c.close();
        }
        catch (Exception e){
            Toast.makeText(ThayDoiThongTinCaNhan.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(ThayDoiThongTinCaNhan.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
    }
}