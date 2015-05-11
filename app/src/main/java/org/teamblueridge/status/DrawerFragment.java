package org.teamblueridge.status;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends Fragment {

    private static String[] mTitles;
    private static int[] mResources;
    private static List<Drawable> mIcons = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawerLayout;
    private DrawerAdapter mAdapter;
    private View mView;
    private DrawerListener mListener;

    public DrawerFragment() {
    }

    public static List<DrawerItem> getItems() {
        List<DrawerItem> items = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            DrawerItem item = new DrawerItem();
            item.setTitle(mTitles[i]);
            item.setIcon(mIcons.get(i));
            items.add(item);
        }

        return items;
    }

    public void setListener(DrawerListener listener) {
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResources = new int[]{R.drawable.ic_action_status, R.drawable.ic_action_help};
        for (int resource : mResources) {
            mIcons.add(getActivity().getResources().getDrawable(resource));
        }

        mTitles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawer, container, false);
        ImageView img = (ImageView) rootView.findViewById(R.id.img_nav);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.drawer_list);
        mAdapter = new DrawerAdapter(getActivity(), getItems());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(mView);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    public void init(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        mView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mToggle = new ActionBarDrawerToggle(
                getActivity(),
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ((ActionBarActivity) getActivity()).invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ((ActionBarActivity) getActivity()).invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(mView, slideOffset);
            }
        };

        mDrawerLayout.setDrawerListener(mToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mToggle.syncState();
            }
        });
    }

    public static interface ClickListener {

        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    public interface DrawerListener {

        public void onDrawerItemSelected(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
    }
}
