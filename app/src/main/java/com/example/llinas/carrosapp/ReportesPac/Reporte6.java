package com.example.llinas.carrosapp.ReportesPac;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.llinas.carrosapp.Carro;
import com.example.llinas.carrosapp.CarroSQLite;
import com.example.llinas.carrosapp.R;

import java.util.ArrayList;

public class Reporte6 extends AppCompatActivity {

    private TextView numCarros;
    Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte6);
        res = getResources();
        numCarros = (TextView)findViewById(R.id.caro);


        numCarros.setText(inicializarDatos() + "");
    }

    public String inicializarDatos(){
        ArrayList<Carro> personas = new ArrayList<>();
        Carro p;
        SQLiteDatabase db;
        String sql,placa,modelo,marca, color;
        int foto;
        double precio;
        CarroSQLite psoh = new CarroSQLite(this);
        db=psoh.getReadableDatabase();
        double costo = 999999999;
        Carro caro=null;

        sql="Select foto,modelo,marca,placa,color,precio from Carros";
        Cursor c= db.rawQuery(sql, null);

        if(c.moveToFirst()){
            do{
                foto = c.getInt(0);
                modelo=c.getString(1);
                marca=c.getString(2);
                placa=c.getString(3);
                color=c.getString(4);
                precio=c.getDouble(5);
                p= new Carro(foto,modelo,marca,placa, color,precio);
                if(precio<costo){
                    costo = precio;
                    caro = p;
                }
                personas.add(p);

            }while(c.moveToNext());
        }

        if(caro!=null) {
            return "Marca: " + caro.getMarca() + "\n"+"Placa: "+ caro.getPlaca()+"\n"+"Modelo: "+ caro.getModelo()+"\n"+" Color: " +caro.getColor()+"\n"+"Precio: "+caro.getPrecio();
        }else{
            return "No Hay Vehiculos Agregados";
        }
    }


}
