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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Categories;
import br.com.escala.app.model.MagazineContentsRelated;
import br.com.escala.app.view.ContentSelectedCategoryActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;
import butterknife.OnTouch;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.CategoriesViewHolder>{

    private List<Categories> categoriesList;

    public AdapterCategories() {
        categoriesList = new ArrayList<>();
    }

    public AdapterCategories(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new AdapterCategories.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        holder.bind(categoriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return (categoriesList != null && categoriesList.size() > 0) ? categoriesList.size() : 0;
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_item_category)
        ImageView imgItemCategory;
        @BindView(R.id.text_item_category)
        TextView textItemCategory;
        @BindView(R.id.progressBar_item_category)
        ProgressBar progressBarCategory;

        public CategoriesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(Categories categories){

            textItemCategory.setText(categories.getMagazineCatName());
            ImageUtil.loadImage(Constantes.URL_BASE_CATEGORY_COVERS + categories.getMagazineCatCover(), imgItemCategory, progressBarCategory, R.drawable.logo);

            imgItemCategory.setOnClickListener(v -> {

                Intent intent = new Intent(v.getContext(), ContentSelectedCategoryActivity.class);
                intent.putExtra("ID_CATEGORY", categories.getMagazineCatId());
                v.getContext().startActivity(intent);

            });

        }

    }

    public void setCategoriesList(List<Categories> categories){
        this.categoriesList = categories;
        notifyDataSetChanged();
    }
}
