package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResponse {

    @Expose
    String text;

    @Expose
    boolean favorited;

    @Expose
    boolean retweeted;

    @SerializedName("retweet_count")
    @Expose
    int retweetCount;

    @SerializedName("favorite_count")
    @Expose
    int favoriteCount;

    SearchUserResponse user;

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public void setRetweeted(boolean retweeted) {
        this.retweeted = retweeted;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(SearchUserResponse user) {
        this.user = user;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isRetweeted() {
        return retweeted;
    }

    public String getText() {
        return text;
    }

    public SearchUserResponse getUser() {
        return user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

}
