package br.com.escala.app.adapters;

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

        private ProgressBar progressBarCategory;
        private ImageView imageViewCover;
        private TextView textViewMagazine, textViewLaunchDate;

        public SelectedCategoryViewHolder(View itemView) {
            super(itemView);

            imageViewCover = itemView.findViewById(R.id.img_category);
            textViewLaunchDate = itemView.findViewById(R.id.txt_edition_month_id);
            textViewMagazine = itemView.findViewById(R.id.txt_name_magazine);
            progressBarCategory = itemView.findViewById(R.id.progress_bar_category);

        }

        public void bind (Revista revista){

            ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imageViewCover, progressBarCategory, R.drawable.logo);

            Log.i("TAG", "bindTEXTOLANCAMENTO: " + revista.getDataRegistro());

            textViewMagazine.setText(revista.getNomeRevista());
            textViewLaunchDate.setText(revista.getDataRegistro());

        }

    }

    public void setRevistaList(List<Revista> revistas) {
        this.revistaList = revistas;
        notifyDataSetChanged();
    }
}