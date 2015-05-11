package org.teamblueridge.status;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StatusFragment extends Fragment {

    View rootView;

    public StatusFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_status, container, false);

        final TextView main = (TextView) rootView.findViewById(R.id.main_site1);
        final TextView paste = (TextView) rootView.findViewById(R.id.paste1);
        final TextView wiki = (TextView) rootView.findViewById(R.id.wiki1);
        final TextView gerrit = (TextView) rootView.findViewById(R.id.gerrit1);
        final TextView project = (TextView) rootView.findViewById(R.id.projects1);
        final TextView jenkins = (TextView) rootView.findViewById(R.id.jenkins1);
        final TextView fserver = (TextView) rootView.findViewById(R.id.file_server1);
        final TextView bb = (TextView) rootView.findViewById(R.id.bb1);

        File file = new File("/sdcard/tbrstatus.txt");

        if (file.exists()) {

            try {
                main.setText(FileUtils.readLines(file).get(0));
                paste.setText(FileUtils.readLines(file).get(1));
                wiki.setText(FileUtils.readLines(file).get(2));
                gerrit.setText(FileUtils.readLines(file).get(3));
                project.setText(FileUtils.readLines(file).get(4));
                jenkins.setText(FileUtils.readLines(file).get(5));
                fserver.setText(FileUtils.readLines(file).get(6));
                bb.setText(FileUtils.readLines(file).get(7));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rootView;
    }
};