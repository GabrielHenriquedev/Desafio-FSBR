package com.ClientManager.clientcrud.service;


import java.io.IOException;

import com.ClientManager.clientcrud.models.ClientModels;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;


public  abstract class CepServiceUtil {

    private CepServiceUtil() {

    }
    public static ClientModels getEndereco(String cep) throws IOException {

        ClientModels end = null;
        HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                end = gson.fromJson(result, ClientModels.class);
            }

        }

        return end;
    }

    public static void getCep (String cep) throws IOException {

        ClientModels endereco = getEndereco(cep);
        System.out.println(endereco != null ? endereco.toString() : "Endereço não encontrado.");
    }
}

