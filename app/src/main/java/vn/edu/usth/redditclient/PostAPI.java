package vn.edu.usth.redditclient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.edu.usth.redditclient.data.Post;

public interface PostAPI {
    String BASE_URL = "https://www.reddit.com/r/";

    @GET("{feed_name}/.rss")
    Call<Post> getPost(@Path("feed_name") String feed_name);
}
