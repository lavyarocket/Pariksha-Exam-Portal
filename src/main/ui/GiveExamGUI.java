package ui;

import model.Student;
import ui.exceptions.EndOfExamException;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

//GiveExamGUI class is the page used by student to attempt exams created by instructors
public class GiveExamGUI {
    JFrame mainframe;
    JLabel heading;
    Account account = new Account();
    JButton goBack;
    JButton submit;
    JLabel questionLabel;
    JLabel answerLabel;
    JTextField answer;
    JList<String> examList;
    DefaultListModel<String> examListModel;
    List<String> list;
    int counter = 0;
    GiveExam giveExam;
    int index;

    //Parameterized Constructor
    public GiveExamGUI(Student student, JFrame mainframe) {
        this.mainframe = mainframe;
        mainframe.setTitle("Give Exam");
        mainframe.getContentPane().removeAll();
        mainframe.repaint();
        giveExam = new GiveExam(student);
        mainframe.setSize(800, 500);
        mainframe.setBackground(Color.BLACK);
        Image icon = Toolkit.getDefaultToolkit().getImage(".data/Program_Icon.png");
        mainframe.setIconImage(icon);
        initializeViewExam();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

    //EFFECTS: Constructs the GUI Elements for the View Exam Scroll Panel Page
    private void initializeViewExam() {
        JPanel viewExamPanel = new JPanel(new GridBagLayout());
        viewExamPanel.setBackground(SystemColor.WHITE);
        GridBagConstraints viewExamPanelConstraints = new GridBagConstraints();
        viewExamPanelConstraints.insets = new Insets(10, 10, 10, 10);
        heading = new JLabel("Select your Exam choice:");
        goBack = new JButton("Go Back to Account");
        list = account.viewExams();
        examListModel = new DefaultListModel<>();
        for (String s : list) {
            examListModel.addElement(s);
        }
        examList = new JList<>();
        examList.setModel(examListModel);
        examList.setSize(new Dimension(800, 300));
        examList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        examList.setVisibleRowCount(4);
        JScrollPane viewExamScrollPane;
        viewExamScrollPane = new JScrollPane(examList, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        viewExamScrollPane.setSize(new Dimension(800, 300));
        viewExamElements(viewExamPanel, viewExamPanelConstraints, viewExamScrollPane);
        mainframe.add(viewExamPanel);
    }

    //EFFECTS: Extension of the method above, it constructs the GUI Elements for the View Exam Scroll Panel Page
    private void viewExamElements(JPanel viewExamPanel, GridBagConstraints constraints, JScrollPane scrollPane) {
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        viewExamPanel.add(heading, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        viewExamPanel.add(scrollPane, constraints);
        ListSelectionListener examSelectionFromList = examSelectionFromList(viewExamPanel);
        examList.addListSelectionListener(examSelectionFromList);
        constraints.gridx = 0;
        constraints.gridy = 2;
        viewExamPanel.add(goBack, constraints);
        ActionListener goBackCall = goBackCall();
        goBack.addActionListener(goBackCall);
    }

    //EFFECTS: Constructs the GUI Elements for the Give Exam Page
    private void initializeGiveExam() {
        giveExam.selectExam(index);
        JPanel giveExamPanel = new JPanel(new GridBagLayout());
        giveExamPanel.setBackground(SystemColor.WHITE);
        GridBagConstraints giveExamPanelConstraints = new GridBagConstraints();
        giveExamPanelConstraints.insets = new Insets(10, 10, 10, 10);
        questionLabel = new JLabel();
        answerLabel = new JLabel();
        answer = new JTextField(10);
        submit = new JButton("Submit Answer");
        try {
            questionLabel.setText("Question " + (counter + 1) + ": " + giveExam.getQuestion(counter));
        } catch (EndOfExamException e) {
            JOptionPane.showMessageDialog(mainframe, "End of Exam. Your final grade is " + giveExam.getGrade());
            new StudentAccountGUI(mainframe);
        }
        answerLabel.setText("Enter Answer " + (counter + 1) + ":");
        giveExamElements(giveExamPanel, giveExamPanelConstraints);
        mainframe.add(giveExamPanel);
    }

    //EFFECTS: Extension of the method above, it constructs the GUI Elements for the Give Exam Page
    private void giveExamElements(JPanel giveExamPanel, GridBagConstraints giveExamPanelConstraints) {
        giveExamPanelConstraints.anchor = GridBagConstraints.CENTER;
        giveExamPanelConstraints.gridx = 0;
        giveExamPanelConstraints.gridy = 0;
        giveExamPanel.add(questionLabel, giveExamPanelConstraints);
        giveExamPanelConstraints.gridx = 0;
        giveExamPanelConstraints.gridy = 1;
        giveExamPanel.add(answerLabel, giveExamPanelConstraints);
        giveExamPanelConstraints.gridx = 1;
        giveExamPanelConstraints.gridy = 1;
        giveExamPanel.add(answer, giveExamPanelConstraints);
        giveExamPanelConstraints.gridx = 0;
        giveExamPanelConstraints.gridy = 2;
        giveExamPanel.add(submit, giveExamPanelConstraints);
        ActionListener submitAnswer = submitAnswer(giveExamPanel);
        submit.addActionListener(submitAnswer);
    }

    //EFFECTS: Adds a ListSelectionListener for selecting the exam from a list
    private ListSelectionListener examSelectionFromList(JPanel viewExamPanel) {
        return e -> {
            index = examList.getSelectedIndex();
            int input = JOptionPane.showConfirmDialog(null,
                    "Click Yes to start Exam number: " + (index + 1), "Exam Confirmation",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            viewExamPanel.setVisible(false);
            if (input == 0) {
                initializeGiveExam();
            } else {
                initializeViewExam();
            }
        };
    }

    //EFFECTS: Adds an Action Listener for the go back Button which takes the control to the Student Account Homepage
    private ActionListener goBackCall() {
        return e -> {
            new StudentAccountGUI(mainframe);
        };
    }

    //EFFECTS: Adds an Action Listener for the Submit Answer Button which submits the entered answer to be checked
    private ActionListener submitAnswer(JPanel panel) {
        return e -> {
            panel.setVisible(false);
            String answerText = answer.getText();
            giveExam.attempt(counter, answerText);
            counter++;
            initializeGiveExam();
        };
    }
}
