package com.example.llinas.carrosapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CarroAdapter extends RecyclerView.Adapter<CarroAdapter.CarroViewHolder>{
    private ArrayList<Carro> carros;

    public CarroAdapter(ArrayList<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public CarroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_carro,parent,false);
        return new CarroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CarroViewHolder holder, int position) {
        Carro p = carros.get(position);

        holder.foto.setImageResource(p.getFoto());
        holder.cajaPlaca.setText(p.getPlaca());
        holder.cajaPrecio.setText(p.getPrecio()+"");
        holder.cajaMarca.setText(p.getMarca());
        holder.cajaModelo.setText(p.getModelo());

    }

    @Override
    public int getItemCount() {
        return carros.size();
    }

    public static class CarroViewHolder extends RecyclerView.ViewHolder{

        private ImageView foto;
        private TextView cajaPlaca;
        private TextView cajaPrecio;
        private TextView cajaMarca;
        private TextView cajaModelo;
        public CarroViewHolder(View itemView) {
            super(itemView);
            foto=(ImageView)itemView.findViewById(R.id.foto);
            cajaPlaca=(TextView)itemView.findViewById(R.id.lblPlaca);
            cajaPrecio=(TextView)itemView.findViewById(R.id.lblPrecio);
            cajaMarca=(TextView)itemView.findViewById(R.id.lblMarca);
            cajaModelo=(TextView)itemView.findViewById(R.id.lblModelo);


        }
    }
}
