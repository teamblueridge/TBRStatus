package org.teamblueridge.status;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class HelpFragment extends Fragment {

    private String carrier,country;

    public HelpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        RelativeLayout support =(RelativeLayout) rootView.findViewById(R.id.mail);
        support.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.setData(Uri.parse("mailto:" + "webmaster@teamblueridge.org"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Service Down");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        return rootView;
    }
}
