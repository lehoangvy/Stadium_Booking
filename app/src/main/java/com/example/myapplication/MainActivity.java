package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME = "DataBaseDatSan.db";
    public static String taiKhoan = "huyhihi";
    ImageButton btn_Qua;
    Button btn_CapNhat,btn_DangXuat,btn_CaiDat,btn_LienHe,btn_QuanLyThe,btn_YeuThich,btn_CauHoi,btn_CongDong;
    TextView txt_Ten,txt_Sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy();
        database = openOrCreateDatabase("DataBaseDatSan.db", MODE_PRIVATE, null);
        thamChieu();
        displayInfor();
        btn_CapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, CapNhatHoSo.class);
                startActivity(myintent);
            }
        });
        btn_DangXuat = findViewById(R.id.btn_DangXuat);
        btn_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder quitDia = new AlertDialog.Builder(MainActivity.this);
                quitDia.setTitle("Question");
                quitDia.setIcon(R.drawable.question);
                quitDia.setMessage("Are you sure you want to logout ?");
                quitDia.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                quitDia.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                quitDia.create().show();
            }
        });

        btn_CaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, CaiDatTaiKhoan.class);
                startActivity(myintent);
            }
        });
        btn_CauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, CauHoiThuongGap.class);
                startActivity(myintent);
            }
        });
        btn_QuanLyThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, QuanLyThe.class);
                startActivity(myintent);
            }
        });
        btn_LienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, LienHe.class);
                startActivity(myintent);
            }
        });

    }
    private void processCopy (){
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath(){
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }


    private void CopyDataBaseFromAsset(){
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);

            String outFileName = getDatabasePath();

            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists())
                f.mkdir();

            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void displayInfor(){
        Cursor c = database.rawQuery("Select * from ThongTinNguoiDung where TaiKhoan='"+taiKhoan+"'",null);
        c.moveToFirst();
        txt_Ten.setText(c.getString(1));
        txt_Sdt.setText(c.getString(2));
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayInfor();
    }
    public void thamChieu(){
        btn_CapNhat = findViewById(R.id.btn_CapNhat);
        btn_CaiDat= findViewById(R.id.btn_CaiDat);
        btn_LienHe = findViewById(R.id.btn_LienHe);
        txt_Ten = findViewById(R.id.txt_Ten);
        txt_Sdt= findViewById(R.id.txt_Sdt);
        btn_CauHoi = findViewById(R.id.btn_CauHoi);
        btn_QuanLyThe = findViewById(R.id.btn_QuanLyThe);
        btn_YeuThich = findViewById(R.id.btn_YeuThich);
    }
}