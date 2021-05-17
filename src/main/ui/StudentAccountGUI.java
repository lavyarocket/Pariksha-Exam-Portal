package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

//StudentAccountGUI class is the Student account page of the program
public class StudentAccountGUI {
    JFrame mainframe;
    Account account = new Account();
    JLabel heading;
    JLabel name;
    JLabel instructorId;
    JButton giveExam;
    JButton viewExams;
    JButton goBack;
    JButton logOut;
    JList<String> examList;
    DefaultListModel<String> examListModel;
    List<String> list;

    //Parameterized Constructor
    public StudentAccountGUI(JFrame mainframe) {
        this.mainframe = mainframe;
        mainframe.setTitle("Student Account");
        mainframe.getContentPane().removeAll();
        mainframe.repaint();
        mainframe.setSize(800, 500);
        mainframe.setBackground(Color.BLACK);
        Image icon = Toolkit.getDefaultToolkit().getImage(".data/Program_Icon.png");
        mainframe.setIconImage(icon);
        initializeAccount();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

    //EFFECTS: Constructs the GUI Elements for the Student Account Page
    private void initializeAccount() {
        JPanel studentHomeScreen = new JPanel(new GridBagLayout());
        studentHomeScreen.setBackground(SystemColor.WHITE);
        GridBagConstraints studentHomeScreenConstraints = new GridBagConstraints();
        studentHomeScreenConstraints.insets = new Insets(10, 10, 10, 10);
        String id = account.getStudentID();
        String nameText = account.getStudentName();
        heading = new JLabel("Student Account Details:");
        name = new JLabel("Name: " + nameText);
        instructorId = new JLabel("Student ID: " + id);
        giveExam = new JButton("Give Exam");
        viewExams = new JButton("View Exams");
        logOut = new JButton("Log Out");
        accountElements(studentHomeScreen, studentHomeScreenConstraints);
        ActionListener logOutCall = logOutCall();
        logOut.addActionListener(logOutCall);
        mainframe.add(studentHomeScreen);
    }

    //EFFECTS: Extension of the methode above, it constructs the GUI Elements for the Student Account Page
    private void accountElements(JPanel studentHomeScreen, GridBagConstraints studentHomeScreenConstraints) {
        studentHomeScreenConstraints.fill = GridBagConstraints.HORIZONTAL;
        studentHomeScreenConstraints.gridx = 0;
        studentHomeScreenConstraints.gridy = 0;
        studentHomeScreen.add(heading, studentHomeScreenConstraints);
        studentHomeScreenConstraints.gridx = 0;
        studentHomeScreenConstraints.gridy = 1;
        studentHomeScreen.add(name, studentHomeScreenConstraints);
        studentHomeScreenConstraints.gridx = 1;
        studentHomeScreenConstraints.gridy = 1;
        studentHomeScreen.add(instructorId, studentHomeScreenConstraints);
        studentHomeScreenConstraints.gridx = 0;
        studentHomeScreenConstraints.gridy = 2;
        studentHomeScreen.add(giveExam, studentHomeScreenConstraints);
        ActionListener giveExamCall = giveExamCall();
        giveExam.addActionListener(giveExamCall);
        studentHomeScreenConstraints.gridx = 0;
        studentHomeScreenConstraints.gridy = 3;
        studentHomeScreen.add(viewExams, studentHomeScreenConstraints);
        ActionListener viewExamCall = viewExamCall(studentHomeScreen);
        viewExams.addActionListener(viewExamCall);
        studentHomeScreenConstraints.gridx = 0;
        studentHomeScreenConstraints.gridy = 4;
        studentHomeScreen.add(logOut, studentHomeScreenConstraints);
    }

    //EFFECTS: Constructs the GUI Elements for the View Exam Scroll Panel Page
    private void initializeViewExams() {
        JPanel viewExam = new JPanel(new GridBagLayout());
        viewExam.setBackground(SystemColor.WHITE);
        GridBagConstraints viewExamConstraints = new GridBagConstraints();
        viewExamConstraints.insets = new Insets(10, 10, 10, 10);
        heading = new JLabel("Exams Created So Far:");
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
        viewExamsElements(viewExam, viewExamConstraints);
        mainframe.add(viewExam);
    }

    //EFFECTS: Extension of the method above, it constructs the GUI Elements for the View Exam Scroll Panel Page
    private void viewExamsElements(JPanel viewExam, GridBagConstraints viewExamConstraints) {
        JScrollPane viewExamScrollPane;
        viewExamScrollPane = new JScrollPane(examList, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        viewExamScrollPane.setSize(new Dimension(800, 300));
        viewExamConstraints.fill = GridBagConstraints.HORIZONTAL;
        viewExamConstraints.gridx = 0;
        viewExamConstraints.gridy = 0;
        viewExam.add(heading, viewExamConstraints);
        viewExamConstraints.gridx = 0;
        viewExamConstraints.gridy = 1;
        viewExam.add(viewExamScrollPane, viewExamConstraints);
        viewExamConstraints.gridx = 0;
        viewExamConstraints.gridy = 2;
        viewExam.add(goBack, viewExamConstraints);
        ActionListener goBackCall = goBackCall(viewExam);
        goBack.addActionListener(goBackCall);
    }

    //EFFECTS: Adds an Action Listener for the logOut Button which takes the control to the HomeScreen
    private ActionListener logOutCall() {
        return e -> new HomeScreenGUI(mainframe);
    }

    //EFFECTS: Adds an Action Listener for the Give Exam Button which takes the control to the Give Exam frame
    private ActionListener giveExamCall() {
        return e -> new GiveExamGUI(account.getStudent(),mainframe);
    }

    //EFFECTS: Adds an Action Listener for the go back Button which takes the control to the Instructor Account Homepage
    private ActionListener goBackCall(JPanel panel) {
        return e -> {
            panel.setVisible(false);
            initializeAccount();
        };
    }

    //EFFECTS: Adds an Action Listener for the View Exam Button which takes the control to the View Exam Panel
    private ActionListener viewExamCall(JPanel panel) {
        return e -> {
            panel.setVisible(false);
            initializeViewExams();
        };
    }

}
