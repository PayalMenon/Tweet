package pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchUserResponse {

    @Expose
    String name;

    @Expose
    @SerializedName("screen_name")
    String screenName;

    @Expose
    String description;

    @Expose
    @SerializedName("followers_count")
    int followers;

    @Expose
    @SerializedName("friends_count")
    int friends;

    @Expose
    @SerializedName("favourites_count")
    int favorites;

    @Expose
    @SerializedName("profile_background_image_url_https")
    String backgroundImageUrl;

    @Expose
    @SerializedName("profile_image_url_https")
    String profileImageUrl;

    @Expose
    @SerializedName("profile_banner_url")
    String bannerUrl;

    @Expose
    @SerializedName("profile_text_color")
    String profileTextColor;

    public int getFavorites() {
        return favorites;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFriends() {
        return friends;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getProfileTextColor() {
        return profileTextColor;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setProfileTextColor(String profileTextColor) {
        this.profileTextColor = profileTextColor;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
