package com.amostras.api_amostras.utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebScrapingUtils {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void abrirBrowser() {
        // Configura o ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:/Program Files/Google/chromedriver-win64/chromedriver.exe");

        // Configurações opcionais do Chrome (headless)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito"); // Executar em modo headless (sem interface gráfica)

        // Inicializa o WebDriver
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Defina o tempo máximo de espera
    }

    public static void fecharBrowser() {
        driver.quit();
    }
  
    // Método para obter URLs das páginas de resultados
    public static void obterUrlsResultados(String bairro, int numeroPaginas) {
      String urlBase = "https://www.wimoveis.com.br/venda/imoveis/df/" + bairro + "/pagina-";
      String urlPagina = urlBase + numeroPaginas;
      driver.get(urlPagina);
  }

    public static int numeroResultados(String url) throws Exception {
        try {
          // Navega para a URL
          driver.get(url);

          // Espera até que o elemento com o número de resultados esteja visível
          WebElement numeroResultadosElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.ThisPostingsTopSection-sc-5z85om-0.euoGfj > div > div.TopLeftSection-sc-5z85om-4.bnElr > h1")));
          String numeroResultadosTexto = numeroResultadosElement.getText();
          return Utils.extrairNumero(numeroResultadosTexto);
      } catch (Exception e) {
          e.printStackTrace();
          throw new Exception("Erro ao conectar ao site", e);
      }
    }

    public static String extrairEndereco(int i) {
        String divPai = "#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.postings-container > div:nth-child("+ i +")";
        try {
            String divChild = "div > div > div.PostingContainer-sc-i1odl-2.iQlPeD > div.PostingTop-sc-i1odl-3.hCsHEP > div:nth-child(1) > div.PostingCardRow-sc-i1odl-5.gJKAik > div > div";
            WebElement enderecoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(divPai +" > "+ divChild)));
            return enderecoElement.getText();
        } catch (TimeoutException e) {
            return "n/a";
        }
    }

    public static String extrairBairro(int i) {
        String divPai = "#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.postings-container > div:nth-child("+ i +")";
        try {
            String divChild = "div > div > div.PostingContainer-sc-i1odl-2.iQlPeD > div.PostingTop-sc-i1odl-3.hCsHEP > div:nth-child(1) > div.PostingCardRow-sc-i1odl-5.gJKAik > div > h2";
            WebElement bairroElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(divPai +" > "+ divChild)));
            return Utils.extraiPrimeiroTexto(bairroElement.getText());
        } catch (TimeoutException e) {
            return "n/a";
        }
    }

    public static String extrairArea(int i) {
        String divPai = "#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.postings-container > div:nth-child("+ i +")";
        try {
            String divChild = "div > div > div.PostingContainer-sc-i1odl-2.iQlPeD > div.PostingTop-sc-i1odl-3.hCsHEP > div:nth-child(1) > div.PostingCardRow-sc-i1odl-5.fPulTZ > h3 > span:nth-child(1)";
            WebElement areaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(divPai +" > "+ divChild)));
            return areaElement.getText().replaceAll("[^0-9,\\.]+", "");
        } catch (TimeoutException e) {
            return "0";
        }
    }

    public static String extrairPreco(int i) {
        String divPai = "#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.postings-container > div:nth-child("+ i +")";
        try {
            String divChild = "div > div > div.PostingContainer-sc-i1odl-2.iQlPeD > div.PostingTop-sc-i1odl-3.hCsHEP > div:nth-child(1) > div.PostingCardRow-sc-i1odl-5.kgqRDY > div > div > div.PriceContainer-sc-12dh9kl-2.ePWLec > div";
            WebElement precoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(divPai +" > "+ divChild)));
            return precoElement.getText().replaceAll("[^0-9,\\.]+", "");
        } catch (TimeoutException e) {
            return "0";
        }
    }

    public static String extrairQuartos(int i) {
        String textoSpanDois = extrairSpan(i, "span:nth-child(2)");
        String textoSpanTres = extrairSpan(i, "span:nth-child(3)");
        String textoSpanQuatro = extrairSpan(i, "span:nth-child(4)");
        String spanDois = Utils.extraiTexto(textoSpanDois);
        String spanTres = Utils.extraiTexto(textoSpanTres);
        String spanQuatro = Utils.extraiTexto(textoSpanQuatro);
        List<String> lista = Arrays.asList("quarto", "quartos");
        return Utils.defineItem(lista, spanDois, spanTres, spanQuatro, textoSpanDois, textoSpanTres, textoSpanQuatro);
    }

    public static String extrairBanheiros(int i) {
        String textoSpanDois = extrairSpan(i, "span:nth-child(2)");
        String textoSpanTres = extrairSpan(i, "span:nth-child(3)");
        String textoSpanQuatro = extrairSpan(i, "span:nth-child(4)");
        String spanDois = Utils.extraiTexto(textoSpanDois);
        String spanTres = Utils.extraiTexto(textoSpanTres);
        String spanQuatro = Utils.extraiTexto(textoSpanQuatro);
        List<String> lista = Arrays.asList("ban", "banheiro");
        return Utils.defineItem(lista, spanDois, spanTres, spanQuatro, textoSpanDois, textoSpanTres, textoSpanQuatro);
    }

    public static String extrairVagas(int i) {
        String textoSpanDois = extrairSpan(i, "span:nth-child(2)");
        String textoSpanTres = extrairSpan(i, "span:nth-child(3)");
        String textoSpanQuatro = extrairSpan(i, "span:nth-child(4)");
        String spanDois = Utils.extraiTexto(textoSpanDois);
        String spanTres = Utils.extraiTexto(textoSpanTres);
        String spanQuatro = Utils.extraiTexto(textoSpanQuatro);
        List<String> lista = Arrays.asList("vaga", "vagas");
        return Utils.defineItem(lista, spanDois, spanTres, spanQuatro, textoSpanDois, textoSpanTres, textoSpanQuatro);
    }

    public static String extrairUrlAmostra(int i) {
        String divPai = "#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.postings-container > div:nth-child("+ i +")";
        try {
            WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(divPai)));
            WebElement divInsideElement = elemento.findElement(By.cssSelector("div:first-child"));
            String amostra = divInsideElement.getAttribute("data-to-posting");
            return "https://www.wimoveis.com.br" + amostra;
        } catch (Exception e) {
            return "0";
        }
    }

    private static String extrairSpan(int i, String childSelector) throws NoSuchElementException {
        String divPai = "#root > div.MainLayoutContainer-sc-ps0squ-0.guYSSN > div > div > div.Content-sc-185xmk8-1.guMLgg > div.ListPostingsContainer-sc-185xmk8-3.eUbYAT > div.postings-container > div:nth-child("+ i +")";
        try {
            String divChild = "div > div > div.PostingContainer-sc-i1odl-2.iQlPeD > div.PostingTop-sc-i1odl-3.hCsHEP > div:nth-child(1) > div.PostingCardRow-sc-i1odl-5.fPulTZ > h3";
            WebElement spanElement = driver.findElement(By.cssSelector(divPai +" > "+ divChild +" > "+ childSelector));
            return spanElement.getText();
        } catch (NoSuchElementException e) {
            return "0";
        }
    }
}
