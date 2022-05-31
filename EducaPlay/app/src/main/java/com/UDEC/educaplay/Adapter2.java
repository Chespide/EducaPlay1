package com.UDEC.educaplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHoldernotas> {
    ArrayList<Notas> listanotas;

    public Adapter2(ArrayList<Notas> listanotas) {
        this.listanotas = listanotas;
    }

    @NonNull
    @Override
    public ViewHoldernotas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cuadritocalificaciones, null,false);
        return new ViewHoldernotas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoldernotas holder, int position) {
        holder.titulo.setText(listanotas.get(position).getNivel());
        holder.cant.setText(listanotas.get(position).getCant());
        holder.buenas.setText(listanotas.get(position).getCorrectas());
        holder.malas.setText(listanotas.get(position).getIncorrectas());
        holder.nota.setText(listanotas.get(position).getPuntaje());

    }

    @Override
    public int getItemCount() {
        return listanotas.size();
    }

    public class ViewHoldernotas extends RecyclerView.ViewHolder {
        TextView titulo, cant,buenas,malas,nota;
        public ViewHoldernotas(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.titulo_calificacion);
            cant=itemView.findViewById(R.id.cantidadpreguntas);
            buenas=itemView.findViewById(R.id.correctas);
            malas=itemView.findViewById(R.id.incorrectas);
            nota=itemView.findViewById(R.id.puntaje);
        }
    }
}
