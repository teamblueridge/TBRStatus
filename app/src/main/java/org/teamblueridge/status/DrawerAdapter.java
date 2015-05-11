package org.teamblueridge.status;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.CustomHolder> {

    List<DrawerItem> mItems = Collections.emptyList();

    private LayoutInflater mInflater;
    private Context mContext;

    public DrawerAdapter(Context context, List<DrawerItem> items) {
        mItems = items;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public DrawerAdapter.CustomHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.drawer_row, viewGroup, false);
        CustomHolder holder = new CustomHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.CustomHolder customHolder, int i) {
        DrawerItem currentItem = mItems.get(i);
        customHolder.mTitle.setText(currentItem.getTitle());
        customHolder.mIcon.setImageDrawable(currentItem.getIcon());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder {

        ImageView mIcon;
        TextView mTitle;

        public CustomHolder(View view) {
            super(view);
            mIcon = (ImageView) view.findViewById(R.id.nav_icon);
            mTitle = (TextView) view.findViewById(R.id.title);
        }
    }
}
