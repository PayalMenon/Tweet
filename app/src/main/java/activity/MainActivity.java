package activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import pojo.AuthResponse;
import pojo.SearchList;
import pojo.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.TweetsService;
import tweets.android.example.com.tweets.R;

public class MainActivity extends AppCompatActivity {

    private static final String AUTH_TOKEN_BASE_URL = "https://api.twitter.com";
    private static final String SEARCH_BASE_URL = "https://api.twitter.com/1.1/search/tweets.json";
    private static final String CONSUMER_KEY = "JvaFYDA5g1nfTwLp7il6xNkCS";
    private static final String CONSUMER_SECRET = "87WXHzooeZhphco2SztfQjOeRtrjcXrfg9lQoAwJxZPYWd3MH5";

    private List<SearchResponse> searchResponseList = new ArrayList<>();
    private TextView searchTweetView;
    private RecyclerView tweetsList;
    TweetsListAdapter adapter;
    private LinearLayoutManager manager;
    private String authToken;
    TweetsService service;
    private String maxIdString;
    private String tweetString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        searchTweetView = (TextView) findViewById(R.id.search_tweet);

        tweetsList = (RecyclerView) findViewById(R.id.tweets_list);

        // attempt to load the next 15 tweets, but the code is half baked. Need to work more on it
       /* tweetsList.setOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int currentPosition = manager.findFirstCompletelyVisibleItemPosition();
                if (currentPosition % 15 >= 13) {
                    getTweetsList(maxIdString);
                }
            }
        });*/
        manager = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        tweetsList.setLayoutManager(manager);
        tweetString = "#recent";
        getAuthToken();
    }

    private void getAuthToken() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(AUTH_TOKEN_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        String authorization = getAuthorizationValue();

        service = retrofit.create(TweetsService.class);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "grant_type=client_credentials");
        Call<AuthResponse> call = service.getAuthToken(body, authorization);
        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();

                if (authResponse.getTokenType().equals("bearer")) {
                    authToken = "Bearer " + authResponse.getAccessToken();
                    getTweetsList();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                String error = t.toString();
                System.out.println(error);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search For a tweet");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tweetString = query;
                getTweetsList();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void getTweetsList() {
        StringBuilder url = new StringBuilder();
        url.append(SEARCH_BASE_URL);
        Call<SearchList> callSearch = null;
        if (maxIdString != null) {
            callSearch = service.getSearchResults(url.toString(), authToken, tweetString, "recent", maxIdString);
        } else {
            callSearch = service.getSearchResults(url.toString(), authToken, tweetString, "recent");
        }
        callSearch.enqueue(new Callback<SearchList>() {
            @Override
            public void onResponse(Call<SearchList> call, Response<SearchList> response) {
                SearchList list = response.body();
                searchResponseList = list.getStatuses();
                System.out.println("List size = " + searchResponseList.size());

                // attempt to load the next 15 tweets, but the code is half baked. Need to work more on it
                /*String nextQuery = list.getSearchMetadata().getNextQuery();
                String queryString = nextQuery.substring((nextQuery.indexOf("?") + 1), nextQuery.length());
                String[] pairs = queryString.split("&");
                Map<String, String> queryPairs = new HashMap<String, String>();
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    queryPairs.put(pair.substring(0, idx), pair.substring(idx + 1));
                }
                maxIdString = queryPairs.get("max_id");
                System.out.println("max_id = " + maxIdString);*/

                if (searchResponseList.size() <= 15) {
                    adapter = new TweetsListAdapter(searchResponseList, MainActivity.this);
                    tweetsList.setAdapter(adapter);

                } else {
                    adapter.notifyDataSetChanged();
                }
                tweetsList.setVisibility(View.VISIBLE);
                searchTweetView.setVisibility(View.GONE);

                System.out.println("list size = " + searchResponseList.size());
            }

            @Override
            public void onFailure(Call<SearchList> call, Throwable t) {
                String error = t.toString();
                System.out.println(error);
            }
        });
    }

    private String getAuthorizationValue() {

        StringBuilder builder = new StringBuilder();
        builder.append(CONSUMER_KEY).append(":").append(CONSUMER_SECRET);
        byte[] data = new byte[0];
        try {
            data = builder.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP);
    }
}
