package ui;

import model.Instructor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//DesignExamGUI class is used by the Instructor to design exams
public class DesignExamGUI {
    JFrame mainframe;
    JLabel heading;
    JLabel numberOfQuestionsLabel;
    JLabel questionLabel;
    JLabel answerLabel;
    JLabel titleLabel;
    JTextField numberOfQuestions;
    JTextField question;
    JTextField answer;
    JTextField title;
    JButton okay;
    JButton add;
    int counter = 1;
    int numberOfQues;
    DesignExam designExam;

    //Parameterized Constructor
    public DesignExamGUI(Instructor instructor,JFrame mainframe) {
        this.mainframe = mainframe;
        mainframe.setTitle("Design Exam");
        mainframe.getContentPane().removeAll();
        mainframe.repaint();
        designExam = new DesignExam(instructor);
        mainframe.setSize(800, 500);
        mainframe.setBackground(Color.BLACK);
        Image icon = Toolkit.getDefaultToolkit().getImage(".data/Program_Icon.png");
        mainframe.setIconImage(icon);
        initializeDesignExam();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

    //EFFECTS: Constructs the GUI Elements for the Design Exam Page
    private void initializeDesignExam() {
        JPanel designExam = new JPanel(new GridBagLayout());
        designExam.setBackground(SystemColor.WHITE);
        GridBagConstraints designExamConstraints = new GridBagConstraints();
        designExamConstraints.insets = new Insets(10, 10, 10, 10);
        heading = new JLabel("Design Exam:");
        numberOfQuestionsLabel = new JLabel("Enter Number of Questions:");
        questionLabel = new JLabel("Enter Question:");
        answerLabel = new JLabel("Enter Answer:");
        titleLabel = new JLabel("Enter Exam Title:");
        numberOfQuestions = new JTextField(10);
        question = new JTextField(10);
        answer = new JTextField(10);
        title = new JTextField(10);
        okay = new JButton("OK");
        designExamElements(designExam, designExamConstraints);
        mainframe.add(designExam);
    }

    //EFFECTS: Extension of the method above , it constructs the GUI Elements for the Design Exam Page
    private void designExamElements(JPanel designExam, GridBagConstraints designExamConstraints) {
        designExamConstraints.anchor = GridBagConstraints.CENTER;
        designExamConstraints.gridx = 0;
        designExamConstraints.gridy = 0;
        designExam.add(heading, designExamConstraints);
        designExamConstraints.gridx = 0;
        designExamConstraints.gridy = 1;
        designExam.add(numberOfQuestionsLabel, designExamConstraints);
        designExamConstraints.gridx = 1;
        designExamConstraints.gridy = 1;
        designExam.add(numberOfQuestions, designExamConstraints);
        designExamConstraints.gridx = 0;
        designExamConstraints.gridy = 2;
        designExam.add(titleLabel, designExamConstraints);
        designExamConstraints.gridx = 1;
        designExamConstraints.gridy = 2;
        designExam.add(title, designExamConstraints);
        designExamConstraints.gridx = 0;
        designExamConstraints.gridy = 3;
        designExam.add(okay, designExamConstraints);
        ActionListener okayCall = okayCall(designExam);
        okay.addActionListener(okayCall);
    }

    //EFFECTS: Constructs the GUI Elements for the Design Questions Page
    private void initializeDesignQuestions() {
        JPanel designQuestions = new JPanel(new GridBagLayout());
        designQuestions.setBackground(SystemColor.WHITE);
        GridBagConstraints designQuestionsConstraints = new GridBagConstraints();
        designQuestionsConstraints.insets = new Insets(10, 10, 10, 10);
        questionLabel = new JLabel("Enter Question");
        answerLabel = new JLabel("Enter Answer");
        question = new JTextField(10);
        answer = new JTextField(10);
        add = new JButton("Add Question to Exam");
        questionLabel.setText("Enter Question " + (counter) + ":");
        answerLabel.setText("Enter Answer " + (counter) + ":");
        designQuestionsElements(designQuestions, designQuestionsConstraints);
        mainframe.add(designQuestions);
    }

    //EFFECTS: Extension of the method above, it constructs the GUI Elements for the Design Questions Page
    private void designQuestionsElements(JPanel designQuestions, GridBagConstraints designQuestionsConstraints) {
        designQuestionsConstraints.anchor = GridBagConstraints.CENTER;
        designQuestionsConstraints.gridx = 0;
        designQuestionsConstraints.gridy = 0;
        designQuestions.add(questionLabel, designQuestionsConstraints);
        designQuestionsConstraints.gridx = 1;
        designQuestionsConstraints.gridy = 0;
        designQuestions.add(question, designQuestionsConstraints);
        designQuestionsConstraints.gridx = 0;
        designQuestionsConstraints.gridy = 1;
        designQuestions.add(answerLabel, designQuestionsConstraints);
        designQuestionsConstraints.gridx = 1;
        designQuestionsConstraints.gridy = 1;
        designQuestions.add(answer, designQuestionsConstraints);
        designQuestionsConstraints.gridx = 0;
        designQuestionsConstraints.gridy = 2;
        designQuestions.add(add, designQuestionsConstraints);
        ActionListener addQuestion = addQuestion(designQuestions);
        add.addActionListener(addQuestion);
    }

    //EFFECTS: Adds an Action Listener for the Okay Button which inputs the number of questions in the Exam
    private ActionListener okayCall(JPanel panel) {
        return e -> {
            String titleText = title.getText();
            designExam.addExamDetails(titleText);
            numberOfQues = Integer.parseInt(numberOfQuestions.getText());
            panel.setVisible(false);
            initializeDesignQuestions();
        };
    }

    //EFFECTS: Adds an Action Listener for the Add Question Button which saves the question into the Exam
    private ActionListener addQuestion(JPanel panel) {
        return e -> {
            if (counter < numberOfQues) {
                panel.setVisible(false);
                String ques = question.getText();
                String ans = answer.getText();
                designExam.addQuestion(ques, ans);
                counter++;
                initializeDesignQuestions();
            } else {
                panel.setVisible(false);
                String ques = question.getText();
                String ans = answer.getText();
                designExam.addQuestion(ques, ans);
                counter++;
                designExam.addExamInList();
                JOptionPane.showMessageDialog(mainframe, "All questions added Successfully!");
                new InstructorAccountGUI(mainframe);
            }
        };
    }
}
