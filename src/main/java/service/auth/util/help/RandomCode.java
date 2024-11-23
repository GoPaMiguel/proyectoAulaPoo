package service.auth.util.help;

import java.util.Random;

public class RandomCode {
    public static String generarCodigoAleatorio(String cedula) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        sb.append(cedula);
        for (int i = 0; i < 5; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indiceAleatorio);
            sb.append(caracterAleatorio);
        }

        return sb.toString();
    }
}
