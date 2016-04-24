package com.domain.yandexapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.domain.yandexapp.R;
import com.domain.yandexapp.model.Artist;
import com.domain.yandexapp.ui.interfaces.OnItemClickListener;
import com.domain.yandexapp.utils.Converter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistsListAdapter extends RecyclerView.Adapter<ArtistsListAdapter.ArtistViewHolder> {

    private Context context;
    private List<Artist> artists;
    private OnItemClickListener onItemClickListener;

    public ArtistsListAdapter(Context context, List<Artist> artists, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.artists = artists;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artists_list, parent, false);
        return new ArtistViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);

        Picasso.with(context)
                .load(artist.getSmallCoverUrl())
                .placeholder(R.drawable.cover_placeholder_small)
                .into(holder.ivCover);
        holder.tvName.setText(artist.getName());
        if (artist.getGenres().length == 0) {
            holder.tvGenres.setVisibility(View.GONE);
        } else {
            holder.tvGenres.setText(Converter.genresToString(artist.getGenres()));
        }
        holder.tvStatistics.setText(
                Converter.albumsToString(artist.getAlbum()) +
                ", " +
                Converter.tracksToString(artist.getTracks()));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale_from_bottom);
        holder.container.startAnimation(animation);
    }

    @Override
    public void onViewDetachedFromWindow(ArtistViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.container.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public void setData(List<Artist> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout container;
        ImageView ivCover;
        TextView tvName;
        TextView tvGenres;
        TextView tvStatistics;

        private OnItemClickListener listener;

        public ArtistViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);

            container = (LinearLayout) itemView.findViewById(R.id.ll_item_container);
            ivCover = (ImageView) itemView.findViewById(R.id.iv_cover);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvGenres = (TextView) itemView.findViewById(R.id.tv_genres);
            tvStatistics = (TextView) itemView.findViewById(R.id.tv_statistics);

            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }

    }

}
