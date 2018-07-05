package br.com.escala.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse implements Parcelable{

    @SerializedName("status")
    private int status;
    @SerializedName("categorias")
    private List<Categories> categories;

    public CategoryResponse() {
    }

    public CategoryResponse(int status, List<Categories> categories) {
        this.status = status;
        this.categories = categories;
    }

    protected CategoryResponse(Parcel in) {
        status = in.readInt();
        categories = in.createTypedArrayList(Categories.CREATOR);
    }

    public static final Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {
        @Override
        public CategoryResponse createFromParcel(Parcel in) {
            return new CategoryResponse(in);
        }

        @Override
        public CategoryResponse[] newArray(int size) {
            return new CategoryResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeTypedList(categories);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
