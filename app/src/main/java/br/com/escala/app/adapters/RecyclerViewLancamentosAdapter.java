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
import br.com.escala.app.view.ApresentacaoRevistaActivity;
import br.com.escala.app.view.EdicaoAtualActivity;
import br.com.escala.app.view.LerEdicaoMesActivity;
import br.com.escala.app.view.PdfViewActivity;
import br.com.escala.app.view.fragment.LancamentosFragment;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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

        public TextView nomeRevista, dataLancamento;
        public ImageView imageViewCover, imageDownloadPdf;
        private Button btnNavigationFree;
        private View view;
        private ProgressBar progressBar;

        public LancamentosViewHolder(View itemView) {
            super(itemView);

            view = itemView;

            nomeRevista = itemView.findViewById(R.id.txt_name_magazine);
            dataLancamento = itemView.findViewById(R.id.txt_edition_month);
            imageViewCover = itemView.findViewById(R.id.img_revista_id);
            progressBar = itemView.findViewById(R.id.progress_bar);
            btnNavigationFree = itemView.findViewById(R.id.btn_nav_free_id);
//            imageDownloadPdf = itemView.findViewById(R.id.img_download_pdf);

        }

        public void bind(Revista revista){

//        Revista revista = revistaList.get(position);

            ImageUtil.loadImage(Constantes.URL_BASE_COVER + revista.getImage(), imageViewCover, progressBar, R.drawable.logo);
            nomeRevista.setText(revista.getNomeRevista());
            dataLancamento.setText(revista.getDataLancamento());

            imageViewCover.setOnClickListener(v -> {

                Intent intent = new Intent(v.getContext(), LerEdicaoMesActivity.class);
                intent.putExtra("REVISTA", revista);
                v.getContext().startActivity(intent);


            });

//            imageDownloadPdf.setOnClickListener(v -> {
//
//                Intent intent = new Intent(v.getContext(), PdfViewActivity.class);
//                intent.putExtra("URL", Constantes.URL_BASE_PDF);
//                intent.putExtra("PATH", revista.getUrlPdfFree());
//                intent.putExtra("FILE_NAME", revista.getId() + ".pdf");
//                v.getContext().startActivity(intent);
//
//            });
        }
    }

    public void setRevistaList(List<Revista> revistas){
        this.revistaList = revistas;
        notifyDataSetChanged();

    }

}
