package br.com.escala.app.adapters;

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
import br.com.escala.app.view.fragment.LancamentosFragment;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewLancamentosAdapter extends RecyclerView.Adapter<RecyclerViewLancamentosAdapter.LancamentosViewHolder>{

    private LancamentosFragment mContext;
    private List<Revista> revistaList;

    public RecyclerViewLancamentosAdapter(LancamentosFragment mContext, List<Revista> revistaList) {
        this.mContext = mContext;
        this.revistaList = revistaList;
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

//        holder.nomeRevista.setText(revistaList.get(position).getNomeRevista());
//        holder.dataLancamento.setText(revistaList.get(position).getDataLancamento());

//        Picasso.get().load("http://cleooficial.com/wp-content/uploads/2018/02/capa-revista-marie-claire-julho-2016-cleo-pires-bancas.jpg").into(holder.capa);

        Revista revista = revistaList.get(position);

        ImageUtil.loadImage(revista.getImage(), holder.imageViewCover, holder.progressBar, R.drawable.logo);


    }

    @Override
    public int getItemCount() {
        return revistaList.size();
    }



    public static class LancamentosViewHolder extends RecyclerView.ViewHolder{

        public TextView nomeRevista, dataLancamento;
        public ImageView imageViewCover;
        private View view;
        private ProgressBar progressBar;

        public LancamentosViewHolder(View itemView) {
            super(itemView);

            view = itemView;
//
//            nomeRevista = itemView.findViewById(R.id.txt_nome_revista);
//            dataLancamento = itemView.findViewById(R.id.txt_mes_lancamento);
            imageViewCover = itemView.findViewById(R.id.img_revista_id);
            progressBar = itemView.findViewById(R.id.progress_bar);


        }
    }
}
