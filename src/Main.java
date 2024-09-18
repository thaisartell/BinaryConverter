import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        renderGUI();
    }

    // Window, processing
    private static void renderGUI() {
        JFrame frame = new JFrame("Binary to Decimal Converter");
        frame.setSize(750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font labelFont = new Font("Arial", Font.PLAIN, 20);
        Font returnFont = new Font("Arial", Font.BOLD, 16);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel intro = new JLabel("Binary Input for Conversion");
        intro.setFont(labelFont);
        intro.setBounds(175, 30, 375, 30);

        JTextField input = new JTextField();
        input.setFont(returnFont);
        input.setBounds(175, 60, 375, 30);

        DocumentFilter filter = new DocumentFilter() {
            public void insertString(FilterBypass bypass, int idx, String input, AttributeSet attribute)
                    throws BadLocationException {
                if (input.matches("[01]*")) {
                    super.insertString(bypass, idx, input, attribute);
                }
            }
            public void replace(FilterBypass bypass, int idx, int length, String input, AttributeSet attribute)
                    throws BadLocationException {
                if (input.matches("[01]*")) {
                    super.replace(bypass, idx, length, input, attribute);
                }
            }
        };

        ((AbstractDocument) input.getDocument()).setDocumentFilter(filter);

        JLabel resultHeader = new JLabel("Decimal Result");
        resultHeader.setFont(labelFont);
        resultHeader.setBounds(175, 110, 375, 30);

        JLabel decimal = new JLabel();
        decimal.setText(String.valueOf(bin_to_dec(input.toString())));
        decimal.setFont(returnFont);
        decimal.setBounds(175, 150, 375, 30);

        panel.add(intro);
        panel.add(input);
        panel.add(resultHeader);
        panel.add(decimal);
        frame.add(panel);
        frame.setVisible(true);
    }

    // Convert binary value to decimal value
    private static int bin_to_dec(String bin) {
        int ans = 0;
//        while(!bin.isEmpty()) {
            for (int i = bin.length() - 1; i >= 0; i--) {
                if (bin.charAt(i) == '0') {
                    continue;
                } else { // digit is 1
                    ans += (2 ^ i);
                }
            }
//        }
        return ans;
    }
}