package ktgk;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CardLayoutMain extends JFrame {

    private int currCard = 1;

    private CardLayout cObjl;

    public CardLayoutMain() {

        setTitle("Card Layout Methods");
        setSize(310, 160);

        // Organize contentPanel
        JPanel cPanel = new JPanel();

        cObjl = new CardLayout();
        cPanel.setLayout(cObjl);

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel4 = new JPanel();

        JLabel jLabel1 = new JLabel("C1");
        JLabel jLabel2 = new JLabel("C2");
        JLabel jLabel3 = new JLabel("C3");
        JLabel jLabel4 = new JLabel("C4");

        jPanel1.add(jLabel1);
        jPanel2.add(jLabel2);
        jPanel3.add(jLabel3);
        jPanel4.add(jLabel4);

        cPanel.add(jPanel1, "1");
        cPanel.add(jPanel2, "2");
        cPanel.add(jPanel3, "3");
        cPanel.add(jPanel4, "4");

        // Organize button panel
        JPanel btnPanel = new JPanel();

        JButton firstButton = new JButton("First");
        JButton nextButton = new JButton("->");
        JButton previousButton = new JButton("<-");
        JButton lastButton = new JButton("Last");

        btnPanel.add(firstButton);
        btnPanel.add(nextButton);
        btnPanel.add(previousButton);
        btnPanel.add(lastButton);

        firstButton.addActionListener((ActionEvent ae) -> {
            cObjl.first(cPanel);
            currCard = 1;
        });

        lastButton.addActionListener((ActionEvent ae) -> {
            cObjl.last(cPanel);
            currCard = 4;
        });

        nextButton.addActionListener((ActionEvent ae) -> {
            if (currCard < 4) {
                currCard = currCard + 1;
                cObjl.show(cPanel, "" + (currCard));
            }
        });

        previousButton.addActionListener((ActionEvent ae) -> {
            if (currCard > 1) {
                currCard = currCard - 1;
                cObjl.show(cPanel, "" + (currCard));
            }
        });

        getContentPane().add(cPanel, BorderLayout.NORTH);

        getContentPane().add(btnPanel, BorderLayout.SOUTH);
    }

    public static void main(String argvs[]) {

        CardLayoutMain cll = new CardLayoutMain();
        cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cll.setVisible(true);
    }
}
