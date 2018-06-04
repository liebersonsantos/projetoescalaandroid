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
import com.example.lieberson.projetoescalaandroid.view.fragment.LancamentosFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewLancamentosAdapter extends RecyclerView.Adapter<RecyclerViewLancamentosAdapter.LancamentosViewHolder>{

    private LancamentosFragment mContext;
    private List<Revistas> revistasList;

    public RecyclerViewLancamentosAdapter(LancamentosFragment mContext, List<Revistas> revistasList) {
        this.mContext = mContext;
        this.revistasList = revistasList;
    }

    @NonNull
    @Override
    public LancamentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lancamentos, parent, false);

        return new LancamentosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LancamentosViewHolder holder, int position) {

        holder.nomeRevista.setText(revistasList.get(position).getNomeRevista());
        holder.dataLancamento.setText(revistasList.get(position).getDataLancamento());

        Picasso.get().load("").into(holder.capa);
    }

    @Override
    public int getItemCount() {
        return revistasList.size();
    }



    public static class LancamentosViewHolder extends RecyclerView.ViewHolder{

        public TextView nomeRevista, dataLancamento;
        public ImageView capa;
        private View view;

        public LancamentosViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            nomeRevista = itemView.findViewById(R.id.txt_nome_revista);
            dataLancamento = itemView.findViewById(R.id.txt_mes_lancamento);
            capa = itemView.findViewById(R.id.img_revista_id);


        }
    }
}
