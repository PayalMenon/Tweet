package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMetadata {

    @SerializedName("max_id_str")
    @Expose
    String maxId;

    @SerializedName("since_id")
    @Expose
    String sinceId;

    @SerializedName("next_results")
    @Expose
    String nextQuery;

    public String getMaxId() {
        return maxId;
    }

    public String getSinceId() {
        return sinceId;
    }

    public String getNextQuery() {
        return nextQuery;
    }

    public void setMaxId(String maxId) {
        this.maxId = maxId;
    }

    public void setSinceId(String sinceId) {
        this.sinceId = sinceId;
    }

    public void setNextQuery(String nextQuery) {
        this.nextQuery = nextQuery;
    }
}
