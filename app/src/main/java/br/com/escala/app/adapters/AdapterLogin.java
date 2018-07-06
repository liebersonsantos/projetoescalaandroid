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

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Revista;
import br.com.escala.app.view.LerEdicaoMesActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;
import java.util.List;

public class AdapterLogin extends RecyclerView.Adapter<AdapterLogin.LancamentosViewHolder>{

    private List<Revista> revistaList;

    public AdapterLogin() {
        revistaList = new ArrayList<>();
    }

//    public AdapterLogin(List<Revista> revistaList) {
//        this.revistaList = revistaList;
//    }

    @NonNull
    @Override
    public AdapterLogin.LancamentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_login, parent, false);

        return new AdapterLogin.LancamentosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLogin.LancamentosViewHolder holder, int position) { //

                holder.bind(revistaList.get(position));
    }

    @Override
    public int getItemCount() {
        return (revistaList != null && revistaList.size() > 0) ? revistaList.size() : 0;
    }

    public static class LancamentosViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.btn_nav_free_id)
        Button btnNavigationFree;
        @BindView(R.id.img_category)
        ImageView imageViewCover;
        @BindView(R.id.progress_bar_category)
        ProgressBar progressBar;

        public LancamentosViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bind(Revista revista){

            ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(),imageViewCover, progressBar, R.drawable.logo);

            btnNavigationFree.setOnClickListener(v -> {

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
