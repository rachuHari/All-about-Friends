package com.compassites.friends.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassites.friends.Model.Friend;
import com.compassites.friends.R;
import com.compassites.friends.UI.WebviewActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by desmond on 31/5/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Friend> mItemList;
    private ImageLoader imageLoader;
    private Context mcontext;

    public RecyclerAdapter() {
        super();
        mItemList = new ArrayList<>();
        imageLoader=ImageLoader.getInstance();
    }

    public void addItems(ArrayList<Friend> list) {
        mItemList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mcontext = viewGroup.getContext();

        View view;

        if (mItemList.get(viewType).isSimple()) {
            view = LayoutInflater.from(mcontext)
                    .inflate(R.layout.recycler_text_row, viewGroup, false);
            return new RecyclerItemViewHolder(view, true);

        } else {
            view = LayoutInflater.from(mcontext)
                    .inflate(R.layout.recycler_image_row, viewGroup, false);
            return new RecyclerItemViewHolder(view, false);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        final RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;

        final Friend friend=mItemList.get(position);
        holder.textTitle.setText(friend.getTitle());
        if (friend.isSimple()) {
            holder.textContent.setText(friend.getContent());
        } else {
            imageLoader.displayImage(friend.getImage_url(), holder.image);

            if (friend.getMore_images_url().length()==0)
                holder.textMore.setVisibility(View.GONE);
            else {
                holder.textMore.setVisibility(View.VISIBLE);
                holder.textMore.setPaintFlags(holder.textMore.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            }

            holder.imageLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, WebviewActivity.class);
                    intent.putExtra("url", friend.getLocation_url());
                    intent.putExtra("title", friend.getLocation_url());
                    mcontext.startActivity(intent);
                }
            });

            holder.textMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mcontext, WebviewActivity.class);
                    intent.putExtra("url", friend.getMore_images_url());
                    intent.putExtra("title", friend.getMore_images_url());
                    mcontext.startActivity(intent);

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private static class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textContent, textTitle, textMore;
        private ImageView image, imageLocation;

        public RecyclerItemViewHolder(View itemView, boolean type) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            if (type) {
                textContent = (TextView) itemView.findViewById(R.id.textDetail);
            } else {
                imageLocation = (ImageView) itemView.findViewById(R.id.imageLocation);
                image = (ImageView) itemView.findViewById(R.id.imagePhoto);
                textMore = (TextView) itemView.findViewById(R.id.textMore);
            }
        }
    }

}

