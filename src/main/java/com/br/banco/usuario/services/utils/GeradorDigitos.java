package com.br.banco.usuario.services.utils;

import java.util.Random;

public class GeradorDigitos {
    public static String gerarDigito(int length) {
        int leftLimit = 48; // digito '0' na tabela ASCII
        int rightLimit = 57; // digito '9' na tabela ASCII
        int targetStringLength = length; // quantidade de digitos
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
}
