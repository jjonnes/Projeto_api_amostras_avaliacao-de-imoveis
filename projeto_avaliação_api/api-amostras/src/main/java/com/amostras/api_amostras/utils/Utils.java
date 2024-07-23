package com.amostras.api_amostras.utils;

import java.util.List;

public class Utils {

  public static double extrairDouble(String texto) throws NumberFormatException {
    texto = texto.replaceAll("[^\\d]+", ""); // Remove todos os caracteres não numéricos

    try {
        return Double.parseDouble(texto);
    } catch (NumberFormatException e) {
        throw new NumberFormatException("Não foi possível converter a string para double: " + texto);
    }

  }
  public static String extraiTexto(String texto) {
      texto = texto.replaceAll("[^a-zA-Z]", ""); 
      return texto;
  }

  public static String extraiPrimeiroTexto(String texto) {
    int indiceVirgula = texto.indexOf(",");
    if (indiceVirgula != -1) {
        return texto.substring(0, indiceVirgula);
    } else {
        return texto;
    }
}

  public static int extrairNumero(String texto) {
    // Extrai apenas números do texto usando regex
    texto = texto.replaceAll("[^0-9]", "");
    return Integer.parseInt(texto);
  }

  public static String defineItem(List<String> arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7 ) {
      if(arg1.contains(arg2)) {
          return arg5;
      } else if(arg1.contains(arg3)) {
          return arg6;
      } else if(arg1.contains(arg4)) {
          return arg7;
      } else {
          return "0";
      }
    }

} 
