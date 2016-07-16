package com.angelcubano.webserviceTest;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by angel on 09/07/16.
 */
public class ConsumeRestService {

    public void consumeUsingHttpURLConnection(){
        try {

            URL url = new URL("http://services.groupkt.com/country/get/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void consumeUsingJAXRS(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://services.groupkt.com/country/get/all");
        Response response = target.request().get();
    }

    public static void main(String args[]){

        ConsumeRestService consumeRestService = new ConsumeRestService();
        //consumeRestService.consumeUsingHttpURLConnection();
        consumeRestService.consumeUsingJAXRS();
    }
}
