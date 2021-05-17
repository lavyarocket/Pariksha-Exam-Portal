package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

//InstructorAccountGUI class is the Instructor account page of the program
public class InstructorAccountGUI {
    JFrame mainframe;
    Account account = new Account();
    JLabel heading;
    JLabel name;
    JLabel instructorId;
    JButton createExam;
    JButton viewExams;
    JButton logOut;
    JButton goBack;
    JList<String> examList;
    List<String> list;
    DefaultListModel<String> examListModel;

    //Parameterized Constructor
    public InstructorAccountGUI(JFrame mainframe) {
        this.mainframe = mainframe;
        mainframe.setTitle("Instructor Account");
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

    //EFFECTS: Constructs the GUI Elements for the Instructor Account Page
    private void initializeAccount() {
        JPanel instructorHomeScreen = new JPanel(new GridBagLayout());
        instructorHomeScreen.setBackground(SystemColor.WHITE);
        GridBagConstraints instructorHomeScreenConstraints = new GridBagConstraints();
        instructorHomeScreenConstraints.insets = new Insets(10, 10, 10, 10);
        String id = account.getInstructorID();
        String nameText = account.getInstructorName();
        heading = new JLabel("Instructor Account Details:");
        name = new JLabel("Name: " + nameText);
        instructorId = new JLabel("Instructor ID: " + id);
        createExam = new JButton("Create Exam");
        viewExams = new JButton("View Exams");
        logOut = new JButton("Log Out");
        instructorHomeScreenConstraints.fill = GridBagConstraints.HORIZONTAL;
        accountElements(instructorHomeScreen, instructorHomeScreenConstraints);
        ActionListener logOutCall = logOutCall();
        logOut.addActionListener(logOutCall);
        mainframe.add(instructorHomeScreen);
    }

    //EFFECTS: Extension of the methode above, it constructs the GUI Elements for the Instructor Account Page
    private void accountElements(JPanel instructorHomeScreen, GridBagConstraints instructorHomeScreenConstraints) {
        instructorHomeScreenConstraints.gridx = 0;
        instructorHomeScreenConstraints.gridy = 0;
        instructorHomeScreen.add(heading, instructorHomeScreenConstraints);
        instructorHomeScreenConstraints.gridx = 0;
        instructorHomeScreenConstraints.gridy = 1;
        instructorHomeScreen.add(name, instructorHomeScreenConstraints);
        instructorHomeScreenConstraints.gridx = 1;
        instructorHomeScreenConstraints.gridy = 1;
        instructorHomeScreen.add(instructorId, instructorHomeScreenConstraints);
        instructorHomeScreenConstraints.gridx = 0;
        instructorHomeScreenConstraints.gridy = 2;
        instructorHomeScreen.add(createExam, instructorHomeScreenConstraints);
        ActionListener createExamCall = createExamCall();
        createExam.addActionListener(createExamCall);
        instructorHomeScreenConstraints.gridx = 0;
        instructorHomeScreenConstraints.gridy = 3;
        instructorHomeScreen.add(viewExams, instructorHomeScreenConstraints);
        ActionListener viewExamCall = viewExamCall(instructorHomeScreen);
        viewExams.addActionListener(viewExamCall);
        instructorHomeScreenConstraints.gridx = 0;
        instructorHomeScreenConstraints.gridy = 4;
        instructorHomeScreen.add(logOut, instructorHomeScreenConstraints);
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

    //EFFECTS: Extension for the method above, it constructs the GUI Elements for the View Exam Scroll Panel Page
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

    //EFFECTS: Adds an Action Listener for the Create Exam Button which takes the control to the Design Exam frame
    private ActionListener createExamCall() {
        return e -> new DesignExamGUI(account.getInstructor(),mainframe);
    }

    //EFFECTS: Adds an Action Listener for the View Exam Button which takes the control to the View Exam Panel
    private ActionListener viewExamCall(JPanel panel) {
        return e -> {
            panel.setVisible(false);
            initializeViewExams();
        };
    }

    //EFFECTS: Adds an Action Listener for the go back Button which takes the control to the Instructor Account Homepage
    private ActionListener goBackCall(JPanel panel) {
        return e -> {
            panel.setVisible(false);
            initializeAccount();
        };
    }

}
