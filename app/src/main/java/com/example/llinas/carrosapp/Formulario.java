package com.example.llinas.carrosapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Random;

public class Formulario extends AppCompatActivity {

    private TextInputEditText cajaPlaca,cajaPrecio;
    private TextInputLayout layPlaca,layPrecio;
    private Spinner spiMarca, spiModelo, spiColor;
    private Resources res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        res= getResources();
        cajaPlaca=(TextInputEditText)findViewById(R.id.placa);
        cajaPrecio=(TextInputEditText)findViewById(R.id.precio);
        spiColor=(Spinner)findViewById(R.id.spColor);
        spiMarca=(Spinner)findViewById(R.id.spMarca);
        spiModelo=(Spinner)findViewById(R.id.spModelo);
        layPlaca=(TextInputLayout)findViewById(R.id.placaCarro);
        layPrecio=(TextInputLayout)findViewById(R.id.precioCarro);

        //Rellenar spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.listaColor));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiColor.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.listaMarca));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiMarca.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.listaModelo));
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiModelo.setAdapter(adapter3);

        //OnFocus - cuando se haga focus en un edittext los demas se cambian a estado normal

        cajaPlaca.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                layPlaca.setError(null);
                layPrecio.setError(null);
            }
        });
        cajaPrecio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                layPlaca.setError(null);
                layPrecio.setError(null);
            }
        });
    }




    public void cancelar(View v){
        Intent i = new Intent(Formulario.this,Principal.class);
        startActivity(i);
    }

    public void guardar(View v){
            if(!cajaPlaca.getText().toString().isEmpty()) {
                if(!cajaPrecio.getText().toString().isEmpty()) {


                    String col = spiColor.getSelectedItem().toString().trim();
                    String pla = cajaPlaca.getText().toString();
                    double pre = Double.parseDouble(cajaPrecio.getText().toString());
                    String mod = spiModelo.getSelectedItem().toString().trim();
                    String mar = spiMarca.getSelectedItem().toString().trim();

                    Carro p = new Carro(foto_aleatoria(), mod, mar, pla,col,pre);
                    p.guardar(this);

                    cajaPrecio.setText("");
                    cajaPlaca.setText("");
                    Snackbar.make(v, res.getString(R.string.alerta6), Snackbar.LENGTH_SHORT).show();
                            layPlaca.setError(null);
                            layPrecio.setError(null);


        }else{
                    layPrecio.setError(res.getString(R.string.alerta5));
                    layPlaca.setError(null);
        }
            }else{
                layPlaca.setError(res.getString(R.string.alerta4));
                layPrecio.setError(null);
            }



    }

    public int foto_aleatoria(){
        int fotos[]= {R.drawable.images,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5};
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada=r.nextInt(5-0);
        return fotos[foto_seleccionada];
    }


    @Override
    public void onBackPressed(){
        Intent i = new Intent(Formulario.this,MainActivity.class);
        startActivity(i);
    }

}
