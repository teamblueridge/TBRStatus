package org.teamblueridge.status;

import android.os.Bundle;
import android.os.Environment;
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

    public HelloFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hello, container, false);

        thread.start();

        return rootView;
    }

    Thread thread = new Thread(new Runnable(){
        @Override
        public void run() {
            try {
                URL url = new URL("https://raw.githubusercontent.com/teamblueridge/TBRStatus/master/tbrstatus.txt");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"tbrstatus.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0; //used to store a temporary size of the buffer
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
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
