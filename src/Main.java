import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.Loader;

import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        try {
            // List of names
            //List<String> names = Arrays.asList("حمیدرضا", "کامبیز", "حسینی", "بختیاری");
            List<String> names = readNamesFromFile("G:\\Rey_commision_bodybuilding\\generate_card_invitation\\14030929\\image_names2.txt");

            Font customFont = loadCustomFont("G:\\Rey_commision_bodybuilding\\generate_card_invitation\\font\\iran_sans.ttf");

            // Font settings
            Font font = customFont.deriveFont(Font.BOLD, 24);
            Color textColor = Color.white;

            // Generate a card for each name
            for (String name : names) {
                // Load the invitation card image
                BufferedImage image = ImageIO.read(new File("G:\\Rey_commision_bodybuilding\\generate_card_invitation\\14030929\\invitation140929.jpg"));

                // Draw the name on the image
                drawName(image, name, font, textColor, 595, 373);

                // Save the modified image with the name as part of the file name
                String outputFileName = "G:\\Rey_commision_bodybuilding\\generate_card_invitation\\14030929\\invitation_Guests_for_1403_09_29\\" + name + ".jpg";
                ImageIO.write(image, "jpg", new File(outputFileName));
            }

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    private static void drawName(BufferedImage image, String name, Font font, Color color, int desiredStartX, int startY) {
        Graphics2D g2d = image.createGraphics();

        // Anti-Aliasing فعال
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(color);
        g2d.setFont(font);

        // محاسبه عرض متن
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(name);

        // موقعیت مناسب برای قرارگیری متن
        int adjustedStartX = Math.max(0, desiredStartX - textWidth);

        // رسم متن
        g2d.drawString(name, adjustedStartX, startY);

        g2d.dispose();
    }

    private static Font loadCustomFont(String fontPath) throws IOException, FontFormatException {
        return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
    }
    private static List<String> readNamesFromFile(String filePath) throws IOException {
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.trim());
            }
        }
        return names;
    }
}
