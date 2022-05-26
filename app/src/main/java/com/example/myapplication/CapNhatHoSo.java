package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CapNhatHoSo extends AppCompatActivity {
    ImageButton btn_back;
    Button btn_ThayCaNhan,btn_ThaySDT;
    TextView txt_hs_Ten_GT,txt_hs_NgaySinh,txt_hs_Email,txt_hs_SDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_ho_so);
        btn_back = findViewById(R.id.btn_back);
        btn_ThayCaNhan = findViewById(R.id.btn_ThayCaNhan);
        btn_ThaySDT = findViewById(R.id.btn_ThaySDT);
        txt_hs_Ten_GT = findViewById(R.id.txt_hs_Ten_GT);
        txt_hs_NgaySinh = findViewById(R.id.txt_hs_NgaySinh);
        txt_hs_Email = findViewById(R.id.txt_hs_Email);
        txt_hs_SDT = findViewById(R.id.txt_hs_SDT);
        displayInfor();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_ThayCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(CapNhatHoSo.this,ThayDoiThongTinCaNhan.class);
                startActivity(myintent);

            }
        });
        btn_ThaySDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(CapNhatHoSo.this,ThayDoiSDT.class);
                startActivity(myintent1);
            }
        });

    }
    public void displayInfor(){
        Cursor c = MainActivity.database.rawQuery("Select * from ThongTinNguoiDung where TaiKhoan='huyhihi'",null);
        c.moveToFirst();
        String gt = "";
        if(c.getInt(4)==1){
            gt="Nam";
        }
        else
            gt="Nữ";
        txt_hs_Ten_GT.setText(c.getString(1)+", "+gt);
        txt_hs_SDT.setText(c.getString(2));
        txt_hs_Email.setText(c.getString(3));
        txt_hs_NgaySinh.setText(c.getString(5));
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayInfor();
    }
}