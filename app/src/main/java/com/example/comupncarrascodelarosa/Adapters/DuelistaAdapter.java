package com.example.comupncarrascodelarosa.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comupncarrascodelarosa.Clases.Duelistas;
import com.example.comupncarrascodelarosa.DetallesDuelistasActivity;
import com.example.comupncarrascodelarosa.R;

import java.util.List;


public class DuelistaAdapter extends RecyclerView.Adapter {
    private List<Duelistas> duelistas;
    private Context context;

    public DuelistaAdapter(List<Duelistas> duelistas, Context context) {
        this.duelistas = duelistas;
        this.context =context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NameViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1) {
            View view = inflater.inflate(R.layout.item_lista_duelista, parent, false);
            viewHolder = new NameViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_lista_progressbar, parent, false);
            viewHolder = new NameViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Duelistas item = duelistas.get(position);

        if(item == null) return;

        View view = holder.itemView;



        TextView tvNombreDuelista = view.findViewById(R.id.txtNombreDuelistaLista);


        tvNombreDuelista.setText(item.getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, DetallesDuelistasActivity.class);
                intent.putExtra("position", item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return duelistas.size();
    }
    @Override
    public int getItemViewType(int position) {
        Duelistas item = duelistas.get(position);
        return item == null ? 0 : 1;
    }

    public void setDuelistas(List<Duelistas> duelistas) {
        this.duelistas = duelistas;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
