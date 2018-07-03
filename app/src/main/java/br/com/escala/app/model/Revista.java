package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Revista implements Parcelable {

    @SerializedName("magazine_id")
    private long id;

    @SerializedName("magazine_cat_id")
    private String categoria_id;

    @SerializedName("magazine_desc")
    private String descricao;

    @SerializedName("magazine_name")
    private String nomeRevista;

    @SerializedName("magazine_data_lanc")
    private String dataLancamento;

    @SerializedName("magazine_cover")
    private String image;

    @SerializedName("magazine_logo")
    private String imageLogo;

    @SerializedName("magazine_register")
    private String dataRegistro;

    @SerializedName("magazine_cat_name")
    private String nomeCategoria;

    @SerializedName("magazine_pdf_free")
    private String urlPdfFree;

    @SerializedName("magazine_pdf_full")
    private String urlPdfFull;

    @SerializedName("magazine_content_online")
    private String contentOnLine;

    @SerializedName("magazine_contents_related")
    private List<MagazineContentsRelated> magazineContentsRelated;

    public Revista() {
    }

    public Revista(long id, String categoria_id, String descricao, String nomeRevista, String dataLancamento, String image, String imageLogo, String dataRegistro, String nomeCategoria, String urlPdfFree, String urlPdfFull, String contentOnLine, List<MagazineContentsRelated> magazineContentsRelated) {
        this.id = id;
        this.categoria_id = categoria_id;
        this.descricao = descricao;
        this.nomeRevista = nomeRevista;
        this.dataLancamento = dataLancamento;
        this.image = image;
        this.imageLogo = imageLogo;
        this.dataRegistro = dataRegistro;
        this.nomeCategoria = nomeCategoria;
        this.urlPdfFree = urlPdfFree;
        this.urlPdfFull = urlPdfFull;
        this.contentOnLine = contentOnLine;
        this.magazineContentsRelated = magazineContentsRelated;
    }

    protected Revista(Parcel in) {
        id = in.readLong();
        categoria_id = in.readString();
        descricao = in.readString();
        nomeRevista = in.readString();
        dataLancamento = in.readString();
        image = in.readString();
        imageLogo = in.readString();
        dataRegistro = in.readString();
        nomeCategoria = in.readString();
        urlPdfFree = in.readString();
        urlPdfFull = in.readString();
        contentOnLine = in.readString();
        magazineContentsRelated = in.createTypedArrayList(MagazineContentsRelated.CREATOR);
    }

    public static final Creator<Revista> CREATOR = new Creator<Revista>() {
        @Override
        public Revista createFromParcel(Parcel in) {
            return new Revista(in);
        }

        @Override
        public Revista[] newArray(int size) {
            return new Revista[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(categoria_id);
        dest.writeString(descricao);
        dest.writeString(nomeRevista);
        dest.writeString(dataLancamento);
        dest.writeString(image);
        dest.writeString(imageLogo);
        dest.writeString(dataRegistro);
        dest.writeString(nomeCategoria);
        dest.writeString(urlPdfFree);
        dest.writeString(urlPdfFull);
        dest.writeString(contentOnLine);
        dest.writeTypedList(magazineContentsRelated);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeRevista() {
        return nomeRevista;
    }

    public void setNomeRevista(String nomeRevista) {
        this.nomeRevista = nomeRevista;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(String imageLogo) {
        this.imageLogo = imageLogo;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getUrlPdfFree() {
        return urlPdfFree;
    }

    public void setUrlPdfFree(String urlPdfFree) {
        this.urlPdfFree = urlPdfFree;
    }

    public String getUrlPdfFull() {
        return urlPdfFull;
    }

    public void setUrlPdfFull(String urlPdfFull) {
        this.urlPdfFull = urlPdfFull;
    }

    public String getContentOnLine() {
        return contentOnLine;
    }

    public void setContentOnLine(String contentOnLine) {
        this.contentOnLine = contentOnLine;
    }

    public List<MagazineContentsRelated> getMagazineContentsRelated() {
        return magazineContentsRelated;
    }

    public void setMagazineContentsRelated(List<MagazineContentsRelated> magazineContentsRelated) {
        this.magazineContentsRelated = magazineContentsRelated;
    }
}

    //    protected Revista(Parcel in) {
//        id = in.readLong();
//        categoria_id = in.readString();
//        descricao = in.readString();
//        nomeRevista = in.readString();
//        dataLancamento = in.readString();
//        image = in.readString();
//        urlPdfFree = in.readString();
//        urlPdfFull = in.readString();
//    }
//
//    public static final Creator<Revista> CREATOR = new Creator<Revista>() {
//        @Override
//        public Revista createFromParcel(Parcel in) {
//            return new Revista(in);
//        }
//
//        @Override
//        public Revista[] newArray(int size) {
//            return new Revista[size];
//        }
//    };
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeLong(id);
//        dest.writeString(categoria_id);
//        dest.writeString(descricao);
//        dest.writeString(nomeRevista);
//        dest.writeString(dataLancamento);
//        dest.writeString(image);
//        dest.writeString(urlPdfFree);
//        dest.writeString(urlPdfFull);
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getCategoria_id() {
//        return categoria_id;
//    }
//
//    public void setCategoria_id(String categoria_id) {
//        this.categoria_id = categoria_id;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//
//    public String getNomeRevista() {
//        return nomeRevista;
//    }
//
//    public void setNomeRevista(String nomeRevista) {
//        this.nomeRevista = nomeRevista;
//    }
//
//    public String getDataLancamento() {
//        return dataLancamento;
//    }
//
//    public void setDataLancamento(String dataLancamento) {
//        this.dataLancamento = dataLancamento;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getUrlPdfFree() {
//        return urlPdfFree;
//    }
//
//    public void setUrlPdfFree(String urlPdfFree) {
//        this.urlPdfFree = urlPdfFree;
//    }
//
//    public String getUrlPdfFull() {
//        return urlPdfFull;
//    }
//
//    public void setUrlPdfFull(String urlPdfFull) {
//        this.urlPdfFull = urlPdfFull;
//    }


//    protected Revista(Parcel in) {
//        id = in.readLong();
//        categoria = in.readString();
//        descricao = in.readString();
//        nomeRevista = in.readString();
//        dataLancamento = in.readString();
//        image = in.readString();
//        urlPdf = in.readString();
//    }
//
//    public static final Creator<Revista> CREATOR = new Creator<Revista>() {
//        @Override
//        public Revista createFromParcel(Parcel in) {
//            return new Revista(in);
//        }
//
//        @Override
//        public Revista[] newArray(int size) {
//            return new Revista[size];
//        }
//    };
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(String categoria) {
//        this.categoria = categoria;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//
//    public String getNomeRevista() {
//        return nomeRevista;
//    }
//
//    public void setNomeRevista(String nomeRevista) {
//        this.nomeRevista = nomeRevista;
//    }
//
//    public String getDataLancamento() {
//        return dataLancamento;
//    }
//
//    public void setDataLancamento(String dataLancamento) {
//        this.dataLancamento = dataLancamento;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public String getUrlPdf() {
//        return urlPdf;
//    }
//
//    public void setUrlPdf(String urlPdf) {
//        this.urlPdf = urlPdf;
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeLong(id);
//        dest.writeString(categoria);
//        dest.writeString(descricao);
//        dest.writeString(nomeRevista);
//        dest.writeString(dataLancamento);
//        dest.writeString(image);
//        dest.writeString(urlPdf);
//    }
