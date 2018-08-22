package com.telecom.deezerAdapter;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.telecom.misc.BooleanHolder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class JsonGetter {

	private static OkHttpClient client = new OkHttpClient().newBuilder()
			.connectTimeout(10, TimeUnit.SECONDS)
			.readTimeout(30, TimeUnit.SECONDS)
			.build(); 

	public static String get(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();
		synchronized(client) {
			try (Response response = client.newCall(request).execute()) {
				return response.body().string();
			}
		}
	}

	public static void downloadMp3(String url, String path) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();
		BooleanHolder taskDone = new BooleanHolder(false);
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				File downloadedFile = new File(path);
				BufferedSink sink = Okio.buffer(Okio.sink(downloadedFile));
				sink.writeAll(response.body().source());
				sink.close();
				taskDone.setTrue();
			}

		});
		// wait for the download to complete
		while(!taskDone.isTrue()) {
			try {
				Thread.sleep(5);
			} catch(InterruptedException e) {}
		}
	}
}