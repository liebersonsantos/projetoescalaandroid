package br.com.escala.app.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.escala.app.R;
import br.com.escala.app.helper.Constantes;
import br.com.escala.app.helper.ImageUtil;
import br.com.escala.app.model.Revista;
import br.com.escala.app.view.PdfViewActivity;

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
//        return revistaList.size();
        return (revistaList != null && revistaList.size() > 0) ? revistaList.size() : 0;
    }

    public static class LancamentosViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewMagazine, textViewLaunchDate;
        private Button btnNavigationFree;
        public ImageView imageViewCover;
        private View view;
        private ProgressBar progressBar;

        public LancamentosViewHolder(View itemView) {
            super(itemView);

            view = itemView;

//            nomeRevista = itemView.findViewById(R.id.txt_nome_revista);
//            dataLancamento = itemView.findViewById(R.id.txt_mes_lancamento);
            imageViewCover = itemView.findViewById(R.id.img_revista_id);
            progressBar = itemView.findViewById(R.id.progress_bar);
            btnNavigationFree = itemView.findViewById(R.id.btn_nav_free_id);

        }

        public void bind(Revista revista){
//            Revista revista = revistaList.get(position);

            ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(),imageViewCover, progressBar, R.drawable.logo);

            btnNavigationFree.setOnClickListener(v -> {

                Intent intent = new Intent(v.getContext(), PdfViewActivity.class);
                intent.putExtra("URL", Constantes.URL_BASE_PDF);
                intent.putExtra("PATH", revista.getUrlPdfFree());
                intent.putExtra("FILE_NAME", revista.getId() + ".pdf");
                v.getContext().startActivity(intent);

            });
        }
    }

    public void setRevistaList(List<Revista> revistas){
        this.revistaList = revistas;
        notifyDataSetChanged();

    }
}
