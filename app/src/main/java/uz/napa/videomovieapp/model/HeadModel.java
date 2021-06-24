package uz.napa.videomovieapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class HeadModel implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<ModelItem> results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_resultd")
    private  int total_resultd;

    public HeadModel(int id, int page, List<ModelItem> results, int total_pages, int total_resultd) {
        this.id = id;
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_resultd = total_resultd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ModelItem> getResults() {
        return results;
    }

    public void setResults(List<ModelItem> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_resultd() {
        return total_resultd;
    }

    public void setTotal_resultd(int total_resultd) {
        this.total_resultd = total_resultd;
    }
}
