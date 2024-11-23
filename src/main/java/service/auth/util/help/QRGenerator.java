package service.auth.util.help;

import uk.org.okapibarcode.backend.QrCode;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.output.Java2DRenderer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRGenerator {

    public static void QR(String code, String codeRandom, String cedula) {

        QrCode qrCode = new QrCode();
        qrCode.setContent(code);

        int width = qrCode.getWidth();
        int height = qrCode.getHeight();

        BufferedImage image = new BufferedImage(width * 8, height * 8, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Java2DRenderer renderer = new Java2DRenderer(g2d, 8, Color.WHITE, Color.BLACK);
        renderer.render(qrCode);
        String url = "src/main/QRS/"+cedula+"/";
        File file = new File(url);
        String ruteComplete = url+codeRandom+".png";

        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Directorio creado: " + url);
            } else {
                System.err.println("Error al crear el file: " + url);
                return;
            }
        }

        try {
            ImageIO.write(image, "png", new File(ruteComplete));
            JOptionPane.showMessageDialog(null, "Generated QR Code");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
