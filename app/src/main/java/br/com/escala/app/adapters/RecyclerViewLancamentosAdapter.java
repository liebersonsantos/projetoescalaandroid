package br.com.escala.app.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Revista;
import br.com.escala.app.view.LerEdicaoMesActivity;
import br.com.escala.app.view.fragment.LancamentosFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewLancamentosAdapter extends RecyclerView.Adapter<RecyclerViewLancamentosAdapter.LancamentosViewHolder>{

    private LancamentosFragment mContext;
    private List<Revista> revistaList;

    public RecyclerViewLancamentosAdapter() {
        revistaList = new ArrayList<>();
    }

//    public RecyclerViewLancamentosAdapter(LancamentosFragment mContext, List<Revista> revistaList) {
//        this.mContext = mContext;
//        this.revistaList = revistaList;
//    }

    @NonNull
    @Override
    public LancamentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_lancamentos, parent, false);

        return new RecyclerViewLancamentosAdapter.LancamentosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewLancamentosAdapter.LancamentosViewHolder holder, int position) {

        holder.bind(revistaList.get(position));
    }

    @Override
    public int getItemCount() {
        return (revistaList != null && revistaList.size() > 0) ? revistaList.size() : 0;
    }

    public static class LancamentosViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_name_magazine)
        TextView nomeRevista;
        @BindView(R.id.txt_edition_month)
        TextView dataLancamento;
        @BindView(R.id.img_category)
        ImageView imageViewCover;
        @BindView(R.id.progress_bar_category)
        ProgressBar progressBar;

        private View view;

        public LancamentosViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
//          imageDownloadPdf = itemView.findViewById(R.id.img_download_pdf);

        }

        public void bind(Revista revista){

            ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imageViewCover, progressBar, R.drawable.logo);
            nomeRevista.setText(revista.getNomeRevista());
            dataLancamento.setText(revista.getDataLancamento());

            imageViewCover.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), LerEdicaoMesActivity.class);
                intent.putExtra("REVISTA", revista);
                v.getContext().startActivity(intent);

            });
        }
    }

    public void setRevistaList(List<Revista> revistas){
        this.revistaList = revistas;
        notifyDataSetChanged();

    }

}
