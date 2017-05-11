package com.example.llinas.carrosapp.ReportesPac;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.llinas.carrosapp.Carro;
import com.example.llinas.carrosapp.CarroAdapter;
import com.example.llinas.carrosapp.CarroSQLite;
import com.example.llinas.carrosapp.R;

import java.util.ArrayList;

public class Reporte4 extends AppCompatActivity {

    private RecyclerView listado;
    private CarroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte4);

        listado=(RecyclerView)findViewById(R.id.lista);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listado.setLayoutManager(llm);


        adapter = new CarroAdapter(inicializarDatos());
        listado.setAdapter(adapter);
    }

    public ArrayList<Carro> inicializarDatos(){
        ArrayList<Carro> personas = new ArrayList<>();
        Carro p;
        SQLiteDatabase db;
        String sql,placa,modelo,marca, color;
        int foto;
        double precio;
        CarroSQLite psoh = new CarroSQLite(this);
        db=psoh.getReadableDatabase();

        sql="Select foto,modelo,marca,placa,color,precio from Carros";
        Cursor c= db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                foto = c.getInt(0);
                modelo=c.getString(1);
                marca=c.getString(2);
                placa=c.getString(3);
                color=c.getString(4);
                precio=c.getDouble(5);
                p= new Carro(foto,modelo,marca,placa, color,precio);
                int ymodelo = Integer.parseInt(modelo);
                //Si el modelo esta entre 2010 y 2015  agrega al carro al listado
                if(ymodelo >= 2010 && ymodelo <= 2015 ) {
                    personas.add(p);
                }
            }while(c.moveToNext());
        }

        return personas;
    }
}
