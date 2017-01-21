package udacity.nanodegree.android.p1.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple data trasnfer class to handle data from json.
 */
public class Page {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("total_pages")
    private Integer totalPages;


    public Integer getPage() {
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
    }


    public List<Result> getResults() {
        return results;
    }


    public void setResults(List<Result> results) {
        this.results = results;
    }


    public Integer getTotalResults() {
        return totalResults;
    }


    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }


    public Integer getTotalPages() {
        return totalPages;
    }


    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
