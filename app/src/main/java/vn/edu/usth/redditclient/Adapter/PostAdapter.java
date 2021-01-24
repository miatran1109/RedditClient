package vn.edu.usth.redditclient.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.usth.redditclient.R;
import vn.edu.usth.redditclient.data.Post;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Post> posts;

    public PostAdapter(Context context, List<Post> posts){
        this.inflater=LayoutInflater.from(context);
        this.posts=posts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fake_news, parent, false);

        ViewHolder view_holder = new ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView  postTitle, postCategory, postUpdated, postAward, postCommunity;
        ImageView postImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.news_title);
            postCategory = itemView.findViewById(R.id.category);
            postUpdated = itemView.findViewById(R.id.updated);
            postAward = itemView.findViewById(R.id.awards);
            postCommunity = itemView.findViewById(R.id.community);
            postImage = itemView.findViewById(R.id.post_img);
        }
    }
}
