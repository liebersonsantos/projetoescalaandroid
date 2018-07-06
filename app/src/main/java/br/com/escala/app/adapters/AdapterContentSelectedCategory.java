package br.com.escala.app.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import br.com.escala.app.model.Revista;
import br.com.escala.app.view.LerEdicaoMesActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterContentSelectedCategory extends RecyclerView.Adapter<AdapterContentSelectedCategory.SelectedCategoryViewHolder> {

    private List<Revista> revistaList;

    public AdapterContentSelectedCategory() {
        revistaList = new ArrayList<>();
    }

//    public AdapterContentSelectedCategory(List<Revista> revistaList) {
//        this.revistaList = revistaList;
//    }

    @NonNull
    @Override
    public AdapterContentSelectedCategory.SelectedCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content_selected_category, parent, false);

        return new AdapterContentSelectedCategory.SelectedCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContentSelectedCategory.SelectedCategoryViewHolder holder, int position) {

        holder.bind(revistaList.get(position));
    }

    @Override
    public int getItemCount() {

        return (revistaList != null && revistaList.size() > 0) ? revistaList.size() : 0;
    }

    public static class SelectedCategoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_category)
        ImageView imageViewCover;
        @BindView(R.id.progress_bar_category)
        ProgressBar progressBarCategory;
        @BindView(R.id.txt_edition_month_id)
        TextView textViewLaunchDate;
        @BindView(R.id.txt_name_magazine)
        TextView textViewMagazine;

        public SelectedCategoryViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        public void bind (Revista revista){

            ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imageViewCover, progressBarCategory, R.drawable.logo);

            Log.i("TAG", "bindTEXTOLANCAMENTO: " + revista.getDataRegistro());

            textViewMagazine.setText(revista.getNomeRevista());
            textViewLaunchDate.setText(revista.getDataRegistro());

            imageViewCover.setOnClickListener(v -> {

                Intent intent = new Intent(v.getContext(), LerEdicaoMesActivity.class);
                intent.putExtra("REVISTA", revista);
                v.getContext().startActivity(intent);

                Log.i("TAG", "bind: RESULTADO REVISTA getDataRegistro " + revista.getDataRegistro());
                Log.i("TAG", "bind: RESULTADO REVISTA getDataLancamento " + revista.getDataLancamento());
                Log.i("TAG", "bind: RESULTADO REVISTA getDescricao " + revista.getDescricao());
                Log.i("TAG", "bind: RESULTADO REVISTA getImageLogo " + revista.getImageLogo());
                Log.i("TAG", "bind: RESULTADO REVISTA getCategoria_id() " + revista.getCategoria_id());
                Log.i("TAG", "bind: RESULTADO REVISTA getMagazineContentsRelated() " + revista.getMagazineContentsRelated());

            });

        }

    }

    public void setRevistaList(List<Revista> revistas) {
        this.revistaList = revistas;
        notifyDataSetChanged();
    }
}