package com.example.demo;

import com.example.demo.dto.Forecast;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.brotli.dec.BrotliInputStream;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherController {

    public static void main(String[] args) throws IOException {
        getWeatherData("dd");
    }

    public static final String uri = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";

    @GetMapping("/weatherData")
    public static Object getWeatherData(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
        try (final CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            ObjectMapper objectMapper =  new ObjectMapper();
            httpGet.setHeader("Accept-Encoding", "br");
            httpGet.setHeader("Content-Type", "application/xml; charset=utf-8");
            httpGet.setHeader("Accept", "application/xml");
            HttpResponse httpResponse = client.execute(httpGet);

            String responseAsString = getResponseBody(httpResponse);
            List<Forecast> forecasts = ResponseParser.parseForecasts(responseAsString);
            return objectMapper.writeValueAsString(forecasts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String getResponseBody(HttpResponse httpResponse) {
        try {
            BufferedReader rd;
            StringBuilder result = new StringBuilder();
            String line = "";
            if (httpResponse.getLastHeader("content-encoding").getValue().equals("br")) {
                rd = new BufferedReader(new InputStreamReader(new BrotliInputStream(httpResponse.getEntity().getContent()),StandardCharsets.UTF_8));
            } else {
                rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(),StandardCharsets.UTF_8));
            }
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
