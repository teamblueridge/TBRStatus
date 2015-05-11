package org.teamblueridge.status;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements DrawerFragment.DrawerListener {

    Drawable oldDrawable;
    DrawerFragment mFragment;
    Toolbar mToolbar;
    View oldView;
    static int rt;

    int[] resources = {R.drawable.ic_action_status_selected, R.drawable.ic_action_help_selected,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FontsOverride.setDefaultFont(this, "MONOSPACE", "Roboto-Medium.ttf");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mFragment.init(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mFragment.setListener(this);
            displayView(-1);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        if (oldView != null) {
            ImageView oldImage = (ImageView) oldView.findViewById(R.id.nav_icon);
            TextView oldText = (TextView) oldView.findViewById(R.id.title);
            oldImage.setImageDrawable(oldDrawable);
                oldText.setTextColor(0xff000000);
        }

        oldView = view;
        ImageView imageView = (ImageView) view.findViewById(R.id.nav_icon);
        TextView textView = (TextView) view.findViewById(R.id.title);
        oldDrawable = imageView.getDrawable();
        imageView.setImageResource(resources[position]);
        textView.setTextColor(0xff2196f3);
        mToolbar.setTitle(textView.getText());
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case -1:
                fragment = new HelloFragment();
                break;
            case 0:
                fragment = new StatusFragment();
                break;
            case 1:
                fragment = new HelpFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }
    }
}
