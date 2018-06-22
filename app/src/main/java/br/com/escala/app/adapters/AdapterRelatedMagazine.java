package br.com.escala.app.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.MagazineContentsRelated;

public class AdapterRelatedMagazine extends RecyclerView.Adapter<AdapterRelatedMagazine.DetailViewHolder>{

    private List<MagazineContentsRelated> revistaList;

    public AdapterRelatedMagazine() {
        revistaList = new ArrayList<>();
    }

    public AdapterRelatedMagazine(List<MagazineContentsRelated> revistaList) {
        this.revistaList = revistaList;
    }

    @NonNull
    @Override
    public AdapterRelatedMagazine.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.item_edicao_mes, parent, false);

        return new AdapterRelatedMagazine.DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {

        holder.bind(revistaList.get(position));

    }

    @Override
    public int getItemCount() {
        return (revistaList != null && revistaList.size() > 0) ? revistaList.size() : 0;
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgDetalhe;
        private TextView txtDetalhe;
        private ProgressBar progressBar;


        public DetailViewHolder(View itemView) {
            super(itemView);

            imgDetalhe = itemView.findViewById(R.id.img_detalhe_revista);
            txtDetalhe = itemView.findViewById(R.id.txt_detalhe_revista);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        public void bind(MagazineContentsRelated revista){

            txtDetalhe.setText(revista.getMagazineContentRelatedDesc() + "...");
            ImageUtil.loadImage(Constantes.URL_BASE_CONTENT_RELATED + revista.getMagazineContentRelatedImg(), imgDetalhe, progressBar, R.drawable.logo);

        }
    }

    public void setRevistaList(List<MagazineContentsRelated> revistas){
        this.revistaList = revistas;
        notifyDataSetChanged();

    }
    //
    //        notifyDataSetChanged();
    //        this.magazineContentsRelateds = detail;
//    public void setDetail(List<MagazineContentsRelated> detail){

//    }
}
