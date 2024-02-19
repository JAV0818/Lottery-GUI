/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliverablee3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class View extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 280;

    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;

    private static final String INPUT_SPECIFIER = "\nEnter six integers "
            + "from 1 through 60, "
            + "separated by one or more spaces."
            + "+ with no leading or white spaces\n";
    private static final String NUM_DRAWINGS_SPECIFIER = "\nEnter "
            + "from 1 through 100000, "
            + "separated by one or more spaces."
            + "+ with no leading or white spaces\n";

    private static JLabel labelUsernums;
    private static JTextField textFieldUsernums;
    private static JLabel labelNumDrawings;
    private static JTextField textFieldNumDrawings;

    private static JButton button;
    private static JTextArea resultArea;

    private static Controller cntl;

    public View() {
        cntl = new Controller(this);

        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);
        resultArea.setText("");

        createTextField();
        createButton();
        createPanel();

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null); // centers frame
        setDefaultCloseOperation(EXIT_ON_CLOSE); // quits when frame closed
        //https://stackoverflow.com/questions/13731710/allowing-the-enter-key-to-press-the-submit-button-as-opposed-to-only-using-mo
        getRootPane().setDefaultButton(button);
        displaySelf();
    }

    private void createTextField() {
        labelUsernums = new JLabel(INPUT_SPECIFIER);
        final int FIELD_WIDTH = 10;
        textFieldUsernums = new JTextField(FIELD_WIDTH);

        labelNumDrawings = new JLabel(NUM_DRAWINGS_SPECIFIER);
        textFieldNumDrawings = new JTextField(FIELD_WIDTH);

    }

    private void createButton() {
        button = new JButton("RUN LOTTO");
        // for the style of lambda expression below, see this video 
        // at Chapter 6, Section 5, from minute 3:25 to minute 3:40.
        // https://catalog.libraries.psu.edu/catalog/37440551    
        button.addActionListener(event -> showLottoResults(textFieldUsernums.getText(),
                textFieldNumDrawings.getText()));
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.add(labelUsernums);
        panel.add(textFieldUsernums);
        panel.add(labelNumDrawings);
        panel.add(textFieldNumDrawings);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        panel.add(button);
        add(panel);
    }

    private void showLottoResults(String nums, String numDrawings) {
        resultArea.setText(cntl.doLotteryDrawings(nums, numDrawings));

    }

    public void showErrorMsg(String howToFixInputError) {
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(), howToFixInputError);
        textFieldUsernums.setText("");
        textFieldUsernums.requestFocus();
    }

    public void displaySelf() {
        this.setVisible(true);
    }
}
