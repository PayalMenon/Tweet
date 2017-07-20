package activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import pojo.SearchResponse;
import pojo.SearchUserResponse;
import tweets.android.example.com.tweets.R;

public class TweetsListAdapter extends RecyclerView.Adapter<TweetsListAdapter.ListHolder> {

    List<SearchResponse> tweetsList;
    Context context;

    public TweetsListAdapter(List<SearchResponse> list, Context context) {
        tweetsList = list;
        this.context = context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet, parent, false);
        return new ListHolder(view);
    }

    @Override
    public int getItemCount() {
        return tweetsList.size();
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        SearchResponse response = tweetsList.get(position);
        SearchUserResponse userData = response.getUser();

        Glide.with(context).load(userData.getProfileImageUrl()).asBitmap().placeholder(R.drawable.tweet)
                .animate(android.R.anim.slide_in_left).into(holder.profileImage);

        holder.nameView.setText(userData.getName());
        holder.screenNameView.setText("#" + userData.getScreenName());
        holder.descriptionView.setText(response.getText());
        holder.retweetText.setText(String.valueOf(response.getRetweetCount()));
        holder.favoriteText.setText(String.valueOf(response.getFavoriteCount()));
        holder.replyText.setVisibility(View.GONE);

        if (null != userData.getBannerUrl()) {
            Glide.with(context).load(userData.getBannerUrl()).asBitmap().into(holder.bannerImage);
        } else {
            holder.bannerImage.setVisibility(View.GONE);
        }
    }

    public static class ListHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        TextView nameView;
        TextView screenNameView;
        TextView descriptionView;
        ImageView bannerImage;
        TextView replyText;
        TextView favoriteText;
        TextView retweetText;

        public ListHolder(View itemView) {
            super(itemView);
            profileImage = (ImageView) itemView.findViewById(R.id.profile_image);
            nameView = (TextView) itemView.findViewById(R.id.name);
            screenNameView = (TextView) itemView.findViewById(R.id.screen_name);
            descriptionView = (TextView) itemView.findViewById(R.id.description);
            bannerImage = (ImageView) itemView.findViewById(R.id.banner);
            replyText = (TextView) itemView.findViewById(R.id.reply);
            favoriteText = (TextView) itemView.findViewById(R.id.favorite);
            retweetText = (TextView) itemView.findViewById(R.id.retweet);
        }
    }
}
