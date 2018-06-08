package com.example.lieberson.projetoescalaandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lieberson.projetoescalaandroid.R;
import com.example.lieberson.projetoescalaandroid.model.Revistas;
import com.example.lieberson.projetoescalaandroid.view.fragment.MinhaBancaFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBanca extends RecyclerView.Adapter<AdapterBanca.BancaViewHolder>{


    private MinhaBancaFragment mContext;
    private List<Revistas> revistasList;

    public AdapterBanca(MinhaBancaFragment mContext, List<Revistas> revistasList) {
        this.mContext = mContext;
        this.revistasList = revistasList;
    }

    @NonNull
    @Override
    public AdapterBanca.BancaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_banca, parent, false);

        return new AdapterBanca.BancaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBanca.BancaViewHolder holder, int position) {

//        holder.nomeRevista.setText(revistasList.get(position).getNomeRevista());
//        holder.dataLancamento.setText(revistasList.get(position).getDataLancamento());

        Picasso.get().load(revistasList.get(position).getUrl_revista()).into(holder.capa);
    }

    @Override
    public int getItemCount() {
        return revistasList.size();
    }



    public static class BancaViewHolder extends RecyclerView.ViewHolder{

        public TextView nomeRevista, dataLancamento;
        public ImageView capa;
        private View view;

        public BancaViewHolder(View itemView) {
            super(itemView);

            view = itemView;

//            nomeRevista = itemView.findViewById(R.id.txt_nome_revista);
//            dataLancamento = itemView.findViewById(R.id.txt_mes_lancamento);
            capa = itemView.findViewById(R.id.imagem_mbanca_id);


        }
    }

}
