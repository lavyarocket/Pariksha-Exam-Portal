package ui;

//import com.apple.eawt.Application;
import ui.exceptions.BadIdException;
import ui.exceptions.BadInstructorEmailException;
import ui.exceptions.BadLoginException;
import ui.exceptions.BadStudentEmailException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//HomeScreenGUI class is the first landing page of the program. It contains the GUI elements and does the navigation
// between different frames and panels
public class HomeScreenGUI extends JFrame {
    JFrame mainframe = new JFrame("Pariksha Exam Portal");
    HomeScreen homeScreenClass = new HomeScreen();
    JButton signUp;
    JButton logIn;
    JButton logInAsInstructor;
    JButton logInAsStudent;
    JButton exit;
    JButton goBack;
    JLabel heading;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel emailLabel;
    JLabel nameLabel;
    JTextField name;
    JLabel idLabel;
    JTextField id;
    JLabel setPasswordLabel;
    JTextField setPassword;
    JTextField email;
    JTextField username;
    JTextField password;
    JButton signUpAsInstructor;
    JButton signUpAsStudent;
    BufferedImage img;
    Image dummyImage;
    ImageIcon logo;
    JLabel mainLogo;
    boolean startup;

    //Parameterized Constructor used by different classes to come back to the HomeScreen
    public HomeScreenGUI(JFrame mainframe) {
        this.mainframe = mainframe;
        mainframe.setTitle("Pariksha Exam Portal");
        mainframe.getContentPane().removeAll();
        mainframe.repaint();
        mainframe.setSize(800, 500);
        mainframe.setBackground(Color.BLACK);
        try {
            img = ImageIO.read(new File("./data/Main_Logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dummyImage = img.getScaledInstance(437, 142, Image.SCALE_SMOOTH);
        logo = new ImageIcon(dummyImage);
        initializeHomeScreen();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

    //Parameterized Constructor used on startup to load the data from the Json Files
    public HomeScreenGUI(boolean startup) {
        this.startup = startup;
        homeScreenClass.getData();
        mainframe.setSize(800, 500);
        mainframe.setBackground(Color.BLACK);
        File imageFile = new File("./data/Program_Icon.png");
        Image icon = null;
        try {
            icon = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Application.getApplication().setDockIconImage(icon);
        mainframe.setIconImage(icon);
        try {
            img = ImageIO.read(new File("./data/Main_Logo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dummyImage = img.getScaledInstance(437, 142, Image.SCALE_SMOOTH);
        logo = new ImageIcon(dummyImage);
        initializeHomeScreen();
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setVisible(true);
    }

    //EFFECTS: Constructs the GUI elements on the HomeScreen
    private void initializeHomeScreen() {
        JPanel homeScreen = new JPanel(new GridBagLayout());
        homeScreen.setBackground(SystemColor.WHITE);
        GridBagConstraints homeScreenConstraints = new GridBagConstraints();
        homeScreenConstraints.insets = new Insets(10, 10, 10, 10);
        signUp = new JButton("Sign Up");
        logIn = new JButton("Log In");
        exit = new JButton("Exit");
        mainLogo = new JLabel(logo);
        homeScreenElements(homeScreen, homeScreenConstraints);
        mainframe.add(homeScreen);
    }

    //EFFECTS: Extension to the method above, includes GUI elements for the HomeScreen
    private void homeScreenElements(JPanel homeScreen, GridBagConstraints homeScreenConstraints) {
        homeScreen.add(mainLogo);
        homeScreenConstraints.anchor = GridBagConstraints.CENTER;
        homeScreenConstraints.gridx = 0;
        homeScreenConstraints.gridy = 0;
        homeScreen.add(mainLogo, homeScreenConstraints);
        homeScreenConstraints.fill = GridBagConstraints.HORIZONTAL;
        homeScreenConstraints.gridx = 0;
        homeScreenConstraints.gridy = 1;
        homeScreen.add(signUp, homeScreenConstraints);
        ActionListener signUpCall = signUpCall(homeScreen);
        signUp.addActionListener(signUpCall);
        homeScreenConstraints.gridx = 0;
        homeScreenConstraints.gridy = 2;
        homeScreen.add(logIn, homeScreenConstraints);
        ActionListener logInCall = logInCall(homeScreen);
        logIn.addActionListener(logInCall);
        homeScreenConstraints.gridx = 0;
        homeScreenConstraints.gridy = 3;
        homeScreen.add(exit, homeScreenConstraints);
        ActionListener exitCall = exitCall();
        exit.addActionListener(exitCall);
    }

    //EFFECTS: Constructs GUI elements for the Login Page
    private void initializeLoginAs() {
        JPanel loginAs = new JPanel(new GridBagLayout());
        loginAs.setBackground(SystemColor.WHITE);
        GridBagConstraints loginAsConstraints = new GridBagConstraints();
        loginAsConstraints.insets = new Insets(10, 10, 10, 10);
        heading = new JLabel("Login:");
        usernameLabel = new JLabel("8-Digit ID:");
        username = new JTextField(10);
        passwordLabel = new JLabel("Password:");
        password = new JPasswordField(10);
        logInAsInstructor = new JButton("Login as Instructor");
        logInAsStudent = new JButton("Login as Student");
        goBack = new JButton("Go Back to Home Screen");
        loginAsElements(loginAs, loginAsConstraints);
        mainframe.add(loginAs);
    }

    //EFFECTS: Extension of the method above, it contains GUI elements for the Login Page
    private void loginAsElements(JPanel loginAs, GridBagConstraints loginAsConstraints) {
        loginAsSetup(loginAs, loginAsConstraints);
        loginAsConstraints.gridx = 1;
        loginAsConstraints.gridy = 2;
        loginAs.add(password, loginAsConstraints);
        loginAsConstraints.gridx = 0;
        loginAsConstraints.gridy = 3;
        loginAs.add(logInAsInstructor, loginAsConstraints);
        ActionListener logInAsInstructorCall = logInAsInstructorCall();
        logInAsInstructor.addActionListener(logInAsInstructorCall);
        loginAsConstraints.gridx = 1;
        loginAsConstraints.gridy = 3;
        loginAs.add(logInAsStudent, loginAsConstraints);
        ActionListener logInAsStudentCall = logInAsStudentCall();
        logInAsStudent.addActionListener(logInAsStudentCall);
        loginAsConstraints.gridx = 0;
        loginAsConstraints.gridy = 4;
        loginAs.add(goBack, loginAsConstraints);
        ActionListener goBackToHomeScreenCall = goBackToHomeScreenCall(loginAs);
        goBack.addActionListener(goBackToHomeScreenCall);
    }

    //EFFECTS: Extension of the method above, it contains GUI elements for the Login Page
    private void loginAsSetup(JPanel loginAs, GridBagConstraints loginAsConstraints) {
        loginAsConstraints.gridx = 0;
        loginAsConstraints.gridy = 0;
        loginAsConstraints.fill = GridBagConstraints.HORIZONTAL;
        loginAs.add(heading, loginAsConstraints);
        loginAsConstraints.gridx = 0;
        loginAsConstraints.gridy = 1;
        loginAs.add(usernameLabel, loginAsConstraints);
        loginAsConstraints.gridx = 1;
        loginAsConstraints.gridy = 1;
        loginAs.add(username, loginAsConstraints);
        loginAsConstraints.gridx = 0;
        loginAsConstraints.gridy = 2;
        loginAs.add(passwordLabel, loginAsConstraints);
    }

    //EFFECTS: Constructs GUI elements for the SignUp Page
    private void initializeSignUpAs() {
        JPanel signUpAs = new JPanel(new GridBagLayout());
        signUpAs.setBackground(SystemColor.WHITE);
        GridBagConstraints signUpAsConstraints = new GridBagConstraints();
        signUpAsConstraints.insets = new Insets(10, 10, 10, 10);
        heading = new JLabel("Sign Up:");
        emailLabel = new JLabel("E-mail address:");
        email = new JTextField(10);
        nameLabel = new JLabel("Name:");
        name = new JTextField(10);
        idLabel = new JLabel("8-Digit ID:");
        id = new JTextField(10);
        setPasswordLabel = new JLabel("Password:");
        setPassword = new JPasswordField(10);
        signUpAsInstructor = new JButton("SignUp as Instructor");
        signUpAsStudent = new JButton("SignUp as Student");
        goBack = new JButton("Go Back to HomeScreen");
        signUpAsElements(signUpAs, signUpAsConstraints);
        mainframe.add(signUpAs);
    }

    //EFFECTS: Extension of the method above, it contains GUI elements for the SignUp Page
    private void signUpAsElements(JPanel signUpAs, GridBagConstraints signUpAsConstraints) {
        signUpAsSetup(signUpAs, signUpAsConstraints);
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 4;
        signUpAs.add(setPasswordLabel, signUpAsConstraints);
        signUpAsConstraints.gridx = 1;
        signUpAsConstraints.gridy = 4;
        signUpAs.add(setPassword, signUpAsConstraints);
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 5;
        signUpAs.add(signUpAsInstructor, signUpAsConstraints);
        ActionListener signUpAsInstructorCall = signUpAsInstructorCall(signUpAs);
        signUpAsInstructor.addActionListener(signUpAsInstructorCall);
        signUpAsConstraints.gridx = 1;
        signUpAsConstraints.gridy = 5;
        signUpAs.add(signUpAsStudent, signUpAsConstraints);
        ActionListener signUpAsStudentCall = signUpAsStudentCall(signUpAs);
        signUpAsStudent.addActionListener(signUpAsStudentCall);
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 6;
        signUpAs.add(goBack, signUpAsConstraints);
        ActionListener goBackToHomeScreenCall = goBackToHomeScreenCall(signUpAs);
        goBack.addActionListener(goBackToHomeScreenCall);
    }

    //EFFECTS: Extension of the method above, it contains GUI elements for the SignUp Page
    private void signUpAsSetup(JPanel signUpAs, GridBagConstraints signUpAsConstraints) {
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 0;
        signUpAsConstraints.fill = GridBagConstraints.HORIZONTAL;
        signUpAs.add(heading, signUpAsConstraints);
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 1;
        signUpAs.add(emailLabel, signUpAsConstraints);
        signUpAsConstraints.gridx = 1;
        signUpAsConstraints.gridy = 1;
        signUpAs.add(email, signUpAsConstraints);
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 2;
        signUpAs.add(nameLabel, signUpAsConstraints);
        signUpAsConstraints.gridx = 1;
        signUpAsConstraints.gridy = 2;
        signUpAs.add(name, signUpAsConstraints);
        signUpAsConstraints.gridx = 0;
        signUpAsConstraints.gridy = 3;
        signUpAs.add(idLabel, signUpAsConstraints);
        signUpAsConstraints.gridx = 1;
        signUpAsConstraints.gridy = 3;
        signUpAs.add(id, signUpAsConstraints);
    }

    //EFFECTS: Adds an Action Listener for the SignUp Button which takes the control to the SignUp Panel
    private ActionListener signUpCall(JPanel homeScreen) {
        return e -> {
            homeScreen.setVisible(false);
            initializeSignUpAs();
        };
    }

    //EFFECTS: Adds an Action Listener for the LogIn Button which takes the control to the Login Panel
    private ActionListener logInCall(JPanel homeScreen) {
        return e -> {
            if (homeScreenClass.checkEmptyInstructor() && homeScreenClass.checkEmptyStudent()) {
                JOptionPane.showMessageDialog(mainframe, "No Accounts have been created yet. \nPlease Sign Up "
                        + "first!");
            } else {
                homeScreen.setVisible(false);
                initializeLoginAs();
            }
        };
    }

    //EFFECTS: Adds an Action Listener for the Go Back to HomeScreen Button which takes the control to the HomeScreen
    // Panel
    private ActionListener goBackToHomeScreenCall(JPanel panel) {
        return e -> {
            panel.setVisible(false);
            initializeHomeScreen();
        };
    }

    //EFFECTS: Adds an Action Listener for the Sign Up as Instructor Button which creates a new entry for an
    // Instructor Account
    private ActionListener signUpAsInstructorCall(JPanel panel) {
        return e -> {
            try {
                String emailText = email.getText();
                String nameText = name.getText();
                String idText = id.getText();
                String passwordText = setPassword.getText();
                if (homeScreenClass.signUpInstructor(emailText, nameText, idText, passwordText)) {
                    JOptionPane.showMessageDialog(mainframe, "Instructor Account Creation Successful!");
                    panel.setVisible(false);
                    initializeHomeScreen();
                }
            } catch (BadInstructorEmailException g) {
                JOptionPane.showMessageDialog(mainframe, "The Instructor email should end with '@instructor.ubc"
                        + ".ca'");
            } catch (BadIdException f) {
                JOptionPane.showMessageDialog(mainframe, "The Instructor ID should 8 digits long");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        };
    }

    //EFFECTS: Adds an Action Listener for the Sign Up as Student Button which creates a new entry for an
    // Student Account
    private ActionListener signUpAsStudentCall(JPanel panel) {
        return e -> {
            try {
                String emailText = email.getText();
                String nameText = name.getText();
                String idText = id.getText();
                String passwordText = setPassword.getText();
                if (homeScreenClass.signUpStudent(emailText, nameText, idText, passwordText)) {
                    JOptionPane.showMessageDialog(mainframe, "Student Account Creation Successful!");
                    panel.setVisible(false);
                    initializeHomeScreen();
                }
            } catch (BadStudentEmailException g) {
                JOptionPane.showMessageDialog(mainframe, "The Student email should end with '@student.ubc"
                        + ".ca'");
            } catch (BadIdException f) {
                JOptionPane.showMessageDialog(mainframe, "The Student ID should 8 digits long");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        };
    }

    //EFFECTS: Adds an Action Listener for the Login as Instructor Button which takes the control to the Instructor
    // Account Panel
    private ActionListener logInAsInstructorCall() {
        return e -> {
            try {
                String id1 = username.getText();
                String passwordText = password.getText();
                if (homeScreenClass.checkInstructorCredentials(id1, passwordText)) {
                    JOptionPane.showMessageDialog(mainframe, "Instructor Login Successful!");
                    //mainframe.dispose();
                    new InstructorAccountGUI(mainframe);
                }
            } catch (BadLoginException g) {
                JOptionPane.showMessageDialog(mainframe, "Invalid ID or Password!");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        };
    }

    //EFFECTS: Adds an Action Listener for the Login as Student Button which takes the control to the Student
    // Account Panel
    private ActionListener logInAsStudentCall() {
        return e -> {
            try {
                String id1 = username.getText();
                String passwordText = password.getText();
                if (homeScreenClass.checkStudentCredentials(id1, passwordText)) {
                    JOptionPane.showMessageDialog(mainframe, "Student Login Successful!");
                    mainframe.dispose();
                    new StudentAccountGUI(mainframe);
                }
            } catch (BadLoginException g) {
                JOptionPane.showMessageDialog(mainframe, "Invalid ID or Password!");
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        };
    }

    //EFFECTS: Adds an Action Listener for the Exit Button which saves the data and closes the program
    private ActionListener exitCall() {
        return e -> {
            homeScreenClass.saveData();
            JOptionPane.showMessageDialog(mainframe, "All Data has been saved Successfully!");
            System.exit(0);
        };
    }

}
