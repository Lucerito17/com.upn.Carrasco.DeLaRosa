package com.example.comupncarrascodelarosa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupncarrascodelarosa.Clases.Cartas;
import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.DetallesCartasActivity;
import com.example.comupncarrascodelarosa.DetallesDuelistasActivity;
import com.example.comupncarrascodelarosa.R;

import java.util.List;

public class CartasAdapter extends RecyclerView.Adapter {
    private List<Cartas> cartas;
    private Context context;

    public CartasAdapter(List<Cartas> cartas, Context context) {
        this.cartas = cartas;
        this.context =context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartasAdapter.NameViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1) {
            View view = inflater.inflate(R.layout.item_lista_cartas, parent, false);
            viewHolder = new NameViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_lista_progressbar, parent, false);
            viewHolder = new NameViewHolder(view);
        }

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cartas item = cartas.get(position);
        if(item == null) return;
        View view = holder.itemView;

        TextView tvNombreCarta = view.findViewById(R.id.txtNombreCarta);

        tvNombreCarta.setText(item.getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, DetallesCartasActivity.class);
                intent.putExtra("position", item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartas.size();
    }
    @Override
    public int getItemViewType(int position) {
        Cartas item = cartas.get(position);
        return item == null ? 0 : 1;
    }

    public void setCartas(List<Cartas> cartas) {
        this.cartas = cartas;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}