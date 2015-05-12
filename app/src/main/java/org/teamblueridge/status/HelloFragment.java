package org.teamblueridge.status;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloFragment extends Fragment {

    static File SDCardRoot = Environment.getExternalStorageDirectory();
    static File file = new File(SDCardRoot, "tbrstatus.json");

    public HelloFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hello, container, false);

        thread.start();

        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "Getting status...", true);
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 3000);

        return rootView;
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL("https://raw.githubusercontent.com/teamblueridge/TBRStatus/master/tbrstatus.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                byte[] buffer = new byte[1024];
                int bufferLength;
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fileOutput.write(buffer, 0, bufferLength);
                }
                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
}