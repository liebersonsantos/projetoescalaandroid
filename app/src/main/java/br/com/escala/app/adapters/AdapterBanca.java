package br.com.escala.app.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.escala.app.R;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Revista;
import br.com.escala.app.view.PdfViewActivity;
import br.com.escala.app.view.fragment.MinhaBancaFragment;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBanca extends RecyclerView.Adapter<AdapterBanca.BancaViewHolder>{


    private MinhaBancaFragment mContext;
    private List<Revista> revistaList;

    public AdapterBanca(MinhaBancaFragment mContext, List<Revista> revistaList) {
        this.mContext = mContext;
        this.revistaList = revistaList;
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

//        holder.nomeRevista.setText(revistaList.get(position).getNomeRevista());
//        holder.dataLancamento.setText(revistaList.get(position).getDataLancamento());

//        Picasso.get().load(revistaList.get(position).getImage()).into(holder.capa);
        Revista revista = revistaList.get(position);

        ImageUtil.loadImage(revista.getImage(), holder.imageViewCover, holder.progressBar, R.drawable.logo);

        holder.imageViewCover.setOnClickListener(v -> {

            Intent intent  = new Intent(v.getContext(), PdfViewActivity.class);
            intent.putExtra("URL", revista.getUrlPdfFree());
            intent.putExtra("FILE", revista.getId() + ".pdf");
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return revistaList.size();
    }



    public static class BancaViewHolder extends RecyclerView.ViewHolder{

        public TextView nomeRevista, dataLancamento;
        public ImageView imageViewCover;
        private View view;
        private ProgressBar progressBar;

        public BancaViewHolder(View itemView) {
            super(itemView);

            view = itemView;

//            nomeRevista = itemView.findViewById(R.id.txt_nome_revista);
//            dataLancamento = itemView.findViewById(R.id.txt_mes_lancamento);
            imageViewCover = itemView.findViewById(R.id.imagem_mbanca_id);
            progressBar = itemView.findViewById(R.id.progress_bar);

        }
    }

}
