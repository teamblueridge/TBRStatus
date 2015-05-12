package org.teamblueridge.status;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StatusFragment extends Fragment {

    View rootView;
    String filename = "tbrstatus.json";

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

            JSONObject parent = parseJSONData();

            if (HelloFragment.file.length() != 0) {

                try {
                main.setText(parent.getJSONObject("Status").getString("main"));
                paste.setText(parent.getJSONObject("Status").getString("paste"));
                wiki.setText(parent.getJSONObject("Status").getString("wiki"));
                gerrit.setText(parent.getJSONObject("Status").getString("gerrit"));
                project.setText(parent.getJSONObject("Status").getString("projects"));
                jenkins.setText(parent.getJSONObject("Status").getString("jenkins"));
                fserver.setText(parent.getJSONObject("Status").getString("fserver"));
                bb.setText(parent.getJSONObject("Status").getString("bb"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        return rootView;
    }

    public JSONObject parseJSONData() {
        String JSONString = null;
        JSONObject JSONObject = null;
        try {
            InputStream inputStream = new FileInputStream(HelloFragment.file);
            //InputStream inputStream = getActivity().getAssets().open(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + filename);
            int sizeOfJSONFile = inputStream.available();
            byte[] bytes = new byte[sizeOfJSONFile];
            inputStream.read(bytes);
            inputStream.close();
            JSONString = new String(bytes, "UTF-8");
            JSONObject = new JSONObject(JSONString);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return JSONObject;
    }
};