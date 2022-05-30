package com.UDEC.educaplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolderDatos> implements  View.OnClickListener{

    ArrayList<Contenido> listdatos;
    private View.OnClickListener listener;

    public Adapter1(ArrayList<Contenido> listdatos){
        this.listdatos = listdatos;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cuadritodocumentos,null,false);
        view.setOnClickListener(this::onClick);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.titulo.setText(listdatos.get(position).getTitulo());
        holder.texto.setText(listdatos.get(position).getTexto());

    }

    @Override
    public int getItemCount() {
        return listdatos.size();
    }

    public  void  setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
           listener.onClick(view);

        }

    }

    class ViewHolderDatos extends  RecyclerView.ViewHolder{
    TextView titulo, texto;
        public ViewHolderDatos(@NonNull View itemView){
            super(itemView);

            titulo=itemView.findViewById(R.id.titulo_cuadrito_inicio);
            texto=itemView.findViewById(R.id.texto_cuadrito_inicio);

        }

    }
}
