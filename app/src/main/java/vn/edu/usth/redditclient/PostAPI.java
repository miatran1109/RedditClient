package vn.edu.usth.redditclient;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.edu.usth.redditclient.data.Post;

public interface PostAPI {
    String BASE_URL = "https://www.reddit.com/r/";

    @GET("news/.rss")
    public Call<Post> getPost();
}
