package br.com.escala.app.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.model.Revista;

public class AdapterDetail extends RecyclerView.Adapter<AdapterDetail.DetailViewHolder>{

    private List<Revista> revistaList;

    public AdapterDetail(List<Revista> revistaList) {
        this.revistaList = revistaList;
    }

    @NonNull
    @Override
    public AdapterDetail.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_edicao_mes, parent, false);

        return new AdapterDetail.DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (revistaList != null && revistaList.size() > 0) ? revistaList.size() : 0;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgDetalhe;
        private EditText edtDetalhe;
        private ProgressBar progressBar;


        public DetailViewHolder(View itemView) {
            super(itemView);

            imgDetalhe = itemView.findViewById(R.id.img_detalhe_revista);
            edtDetalhe = itemView.findViewById(R.id.txt_detalhe_revista);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        public void bind(Revista revista){


        }
    }
}
