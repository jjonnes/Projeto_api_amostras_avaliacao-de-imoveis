package com.amostras.api_amostras.service;

import org.openqa.selenium.TimeoutException;
import org.springframework.stereotype.Service;
import com.amostras.api_amostras.dto.AmostrasDto;
import com.amostras.api_amostras.utils.Utils;
import com.amostras.api_amostras.utils.WebScrapingUtils;

@Service
public class WebScrapingService {


    public int extrairNumeroResultados(String bairro) throws Exception {
        String url = "https://www.wimoveis.com.br/venda/imoveis/df/" + bairro;
        return WebScrapingUtils.numeroResultados(url);
    }

    public AmostrasDto.DadosMercado extrairDados(int i) throws TimeoutException {
        String endereco = WebScrapingUtils.extrairEndereco(i);
        String bairro = WebScrapingUtils.extrairBairro(i);
        String area = WebScrapingUtils.extrairArea(i);
        String preco = WebScrapingUtils.extrairPreco(i);
        String quartos = WebScrapingUtils.extrairQuartos(i);
        String ban = WebScrapingUtils.extrairBanheiros(i);
        String vaga = WebScrapingUtils.extrairVagas(i);
        String urlAmostra = WebScrapingUtils.extrairUrlAmostra(i);

        return new AmostrasDto.DadosMercado(endereco, bairro, Utils.extrairDouble(area), 
            Utils.extrairDouble(preco), Utils.extrairNumero(quartos), Utils.extrairNumero(ban), 
            Utils.extrairNumero(vaga), urlAmostra);
    }

}