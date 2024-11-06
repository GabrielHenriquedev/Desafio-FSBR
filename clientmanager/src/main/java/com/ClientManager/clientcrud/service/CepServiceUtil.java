package com.ClientManager.clientcrud.service;


import java.io.IOException;

import com.ClientManager.clientcrud.exceptions.CepInvalidoException;
import com.ClientManager.clientcrud.models.ClientModels;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;


public  abstract class CepServiceUtil {

    public static ClientModels getCep(String cep) throws IOException, CepInvalidoException {
        ClientModels endereco = null;
        HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                endereco = gson.fromJson(result, ClientModels.class);

                // Verifica se o JSON retornado contém um erro, o que indicaria um CEP inválido
                if (endereco.getLogradouro() == null || endereco.getBairro() == null) {
                    throw new CepInvalidoException("CEP inválido ou não encontrado: " + cep);
                }
            }
        }

        return endereco;
    }


}

