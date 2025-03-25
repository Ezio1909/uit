package ktgk.src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class CardLayoutMain extends JFrame {

    private int currCard = 1;
    private CardLayout cObjl;
    private final List<Map<String, String>> data;

    private List<Map<String, String>> getData() {
        List<Map<String, String>> questions = new ArrayList<>();

        Map<String, String> q1 = new HashMap<>();
        q1.put("id", "1");
        q1.put("question", "What is the capital of France?");
        q1.put("choice1", "Berlin");
        q1.put("choice2", "Madrid");
        q1.put("choice3", "Paris");
        q1.put("choice4", "Rome");
        q1.put("correctanswer", "choice3");
        questions.add(q1);

        Map<String, String> q2 = new HashMap<>();
        q2.put("id", "2");
        q2.put("question", "Which planet is known as the Red Planet?");
        q2.put("choice1", "Earth");
        q2.put("choice2", "Mars");
        q2.put("choice3", "Jupiter");
        q2.put("choice4", "Saturn");
        q2.put("correctanswer", "choice2");
        questions.add(q2);

        Map<String, String> q3 = new HashMap<>();
        q3.put("id", "3");
        q3.put("question", "What is the largest ocean on Earth?");
        q3.put("choice1", "Atlantic Ocean");
        q3.put("choice2", "Indian Ocean");
        q3.put("choice3", "Arctic Ocean");
        q3.put("choice4", "Pacific Ocean");
        q3.put("correctanswer", "choice4");
        questions.add(q3);

        Map<String, String> q4 = new HashMap<>();
        q4.put("id", "4");
        q4.put("question", "Which language is primarily used for Android development?");
        q4.put("choice1", "Kotlin");
        q4.put("choice2", "Swift");
        q4.put("choice3", "JavaScript");
        q4.put("choice4", "Ruby");
        q4.put("correctanswer", "choice1");
        questions.add(q4);

        return questions;
    }

    private JPanel getQuestionPanel(String s) {
        JTextArea questionText = new JTextArea(s);
        questionText.setLineWrap(true);
        questionText.setWrapStyleWord(true);
        questionText.setEditable(false);
        questionText.setOpaque(false);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(questionText, BorderLayout.CENTER);
        return panel;
    }

    private JPanel getMultipleChoicePanel(List<String> choices) {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String choiceString : choices) {
            JRadioButton choice = new JRadioButton(choiceString);
            buttonGroup.add(choice);
            buttonPanel.add(choice);
        }
        return buttonPanel;
    }

    private void addQuestionAnswerArea(JPanel parent, Map<String, String> obj) {
        JPanel jPanel = new JPanel(new BorderLayout());
        JPanel question = getQuestionPanel(obj.get("question"));
        List<String> lst = new ArrayList<>();
        lst.add(obj.get("choice1"));
        lst.add(obj.get("choice2"));
        lst.add(obj.get("choice3"));
        lst.add(obj.get("choice4"));
        JPanel answer = getMultipleChoicePanel(lst);
        String constraint = obj.get("id");
        jPanel.add(question, BorderLayout.NORTH);
        jPanel.add(answer, BorderLayout.SOUTH);
        parent.add(jPanel, constraint);
    }

    public CardLayoutMain() {

        setTitle("Card Layout Methods");
        setSize(500, 220);
        setResizable(false);

        /* Organize questionPanel */
        JPanel questionPanel = new JPanel();

        data = getData();
        cObjl = new CardLayout();
        questionPanel.setLayout(cObjl);

        for (Map<String, String> obj : data) {
            addQuestionAnswerArea(questionPanel, obj);
        }

        /* Organize button control area */
        JPanel controlButtonPanel = new JPanel();
        controlButtonPanel.setLayout(new BoxLayout(controlButtonPanel, BoxLayout.Y_AXIS));
        Dimension squareSize = new Dimension(25, 20);
        JButton buttonCtrl1 = new JButton("1");
        JButton buttonCtrl2 = new JButton("2");
        JButton buttonCtrl3 = new JButton("3");
        JButton buttonCtrl4 = new JButton("4");
        buttonCtrl1.setMaximumSize(squareSize);
        buttonCtrl2.setMaximumSize(squareSize);
        buttonCtrl3.setMaximumSize(squareSize);
        buttonCtrl4.setMaximumSize(squareSize);
        controlButtonPanel.add(buttonCtrl1);
        controlButtonPanel.add(Box.createVerticalStrut(5));
        controlButtonPanel.add(buttonCtrl2);
        controlButtonPanel.add(Box.createVerticalStrut(5));
        controlButtonPanel.add(buttonCtrl3);
        controlButtonPanel.add(Box.createVerticalStrut(5));
        controlButtonPanel.add(buttonCtrl4);

        buttonCtrl1.addActionListener((ActionEvent ae) -> {
            cObjl.show(questionPanel, "1");
        });
        buttonCtrl2.addActionListener((ActionEvent ae) -> {
            cObjl.show(questionPanel, "2");
        });
        buttonCtrl3.addActionListener((ActionEvent ae) -> {
            cObjl.show(questionPanel, "3");
        });
        buttonCtrl4.addActionListener((ActionEvent ae) -> {
            cObjl.show(questionPanel, "4");
        });

        /* Combine quesiton panel and control button into one single panel*/
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        mainPanel.add(controlButtonPanel, BorderLayout.EAST);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* Organize navigation button panel */
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
            cObjl.first(questionPanel);
            currCard = 1;
        });

        lastButton.addActionListener((ActionEvent ae) -> {
            cObjl.last(questionPanel);
            currCard = 4;
        });

        nextButton.addActionListener((ActionEvent ae) -> {
            if (currCard < 4) {
                currCard = currCard + 1;
                cObjl.show(questionPanel, "" + (currCard));
            }
        });

        previousButton.addActionListener((ActionEvent ae) -> {
            if (currCard > 1) {
                currCard = currCard - 1;
                cObjl.show(questionPanel, "" + (currCard));
            }
        });

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

    }

    public static void main(String argvs[]) {

        CardLayoutMain cll = new CardLayoutMain();
        cll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cll.setVisible(true);
    }
}
