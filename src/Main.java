import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

        JLabel resultHeader = new JLabel("Unsigned Decimal Value:");
        resultHeader.setFont(labelFont);
        resultHeader.setBounds(175, 110, 375, 30);

        JLabel decimal = new JLabel();
        decimal.setText(String.valueOf(bin_to_dec(input.getText())));
        decimal.setFont(returnFont);
        decimal.setBounds(175, 140, 375, 30);

        JLabel twosHeader = new JLabel("Two's Complement Decimal Value");
        twosHeader.setFont(labelFont);
        twosHeader.setBounds(175, 170, 425, 30);

        JLabel twosDecimal = new JLabel();
        twosDecimal.setText(String.valueOf(bin_to_twos(input.getText())));
        twosDecimal.setFont(returnFont);
        twosDecimal.setBounds(175, 200, 375, 30);

        input.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                String binary = input.getText();
                decimal.setText(String.valueOf(bin_to_dec(binary)));
                twosDecimal.setText(String.valueOf(bin_to_twos(binary)));
                panel.repaint();
            }
        });

        panel.add(intro);
        panel.add(input);
        panel.add(resultHeader);
        panel.add(twosHeader);
        panel.add(decimal);
        panel.add(twosDecimal);
        frame.add(panel);
        frame.setVisible(true);
    }

    // Convert binary value to decimal value
    private static int bin_to_dec(String bin) {
        int ans = 0;
        if(!bin.isEmpty()) {
            for (int i = 0; i < bin.length(); i++) {
                if (bin.charAt(i) == '1') {
                    ans += (int) Math.pow(2, bin.length() - 1 - i);
                }
            }
        }
        return ans;
    }

    private static String bin_to_twos(String bin){
        //11 = -1, 111 = -1, 100 = -4, 1000 = -8
        int ans = 0;
        String placeholder = "--Ineligible for Two's Complement--";
        if (bin.length() > 1){
            ans -= (int) Math.pow(2, bin.length() - 1);
            for (int i = 1; i < bin.length(); i++){
                if (bin.charAt(i) == '1'){
                    ans += (int) Math.pow(2, bin.length() - 1 -i);
                }
            }
            placeholder = String.valueOf(ans);
        }
        return placeholder;
    }
}