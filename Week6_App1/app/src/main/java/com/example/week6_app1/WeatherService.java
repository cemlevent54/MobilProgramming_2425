package com.example.week6_app1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WeatherService {
    private static final String API_KEY = "get-your-api-key-from-weather-stack";
    private static final String BASE_URL = "https://api.weatherstack.com/current";

    private RequestQueue requestQueue;

    public WeatherService(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public void getWeatherByCity(String cityName, final WeatherCallback callback) {
        String url = BASE_URL + "?access_key=" + API_KEY + "&query=" + cityName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> callback.onSuccess(response),
                error -> callback.onError(error)
        );

        requestQueue.add(jsonObjectRequest);
    }

    public interface WeatherCallback {
        void onSuccess(JSONObject response);
        void onError(VolleyError error);
    }
}
