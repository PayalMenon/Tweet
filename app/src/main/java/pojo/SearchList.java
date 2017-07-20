package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchList {

    @Expose
    List<SearchResponse> statuses;

    @SerializedName("search_metadata")
    SearchMetadata searchMetadata;

    public List<SearchResponse> getStatuses() {
        return statuses;
    }

    public SearchMetadata getSearchMetadata() {
        return searchMetadata;
    }

    public void setSearchMetadata(SearchMetadata searchMetadata) {
        this.searchMetadata = searchMetadata;
    }

    public void setStatuses(List<SearchResponse> statuses) {
        this.statuses = statuses;
    }
}
