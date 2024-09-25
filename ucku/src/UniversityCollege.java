import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UniversityCollege extends JFrame implements ActionListener {
    JLabel jtitle;
    JButton addStudent, reset, deleteRecord, searchButton, generateReportButton;
    JTable studentTable;
    JTextField searchField;
    DefaultTableModel tableModel;

    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "aazam99";
    private Connection connection;

    // Input fields
    private JTextField hallTicketNoField, studentNameField, fatherNameField, motherNameField, 
            dateOfBirthField, casteField, mobileNoField, houseNoField, streetField, postField, 
            villageField, districtField, stateField, pinField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;

    public UniversityCollege() {
        // Frame properties
        setTitle("Principal Office Kakatiya University - Warangal");
        setSize(1250, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
      
        // Set the icon image for the JFrame
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Java Kakatiya University\\ucku\\screenshots\\ku.png");
        setIconImage(icon);

        // Initialize components
        initializeComponents();
        
        // Database connection
        connectToDatabase();
        loadStudentDataFromDatabase();
    }

    private void initializeComponents() {
        // Title
        jtitle = new JLabel("UNIVERSITY COLLEGE :: KAKATIYA UNIVERSITY, WARANGAL, 506009, T.G", JLabel.CENTER);
        jtitle.setFont(new Font("Arial", Font.BOLD, 24));
        add(jtitle, BorderLayout.NORTH);
        jtitle.setForeground(Color.BLUE);

        // Table model
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Hall Ticket No", "Student Name", "Father Name", "Mother Name", 
                "Date of Birth", "Gender", "Caste", "Mobile No", "House No", "Street", "Post", 
                "Village", "District", "State", "Pin"});


        // Table
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        add(inputPanel, BorderLayout.WEST);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Hall Ticket No:"), gbc);
        gbc.gridx = 1;
        hallTicketNoField = new JTextField(15);
        inputPanel.add(hallTicketNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        studentNameField = new JTextField(15);
        inputPanel.add(studentNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Father Name:"), gbc);
        gbc.gridx = 1;
        fatherNameField = new JTextField(15);
        inputPanel.add(fatherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Mother Name:"), gbc);
        gbc.gridx = 1;
        motherNameField = new JTextField(15);
        inputPanel.add(motherNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Date of Birth (dd-MM-yyyy):"), gbc);
        gbc.gridx = 1;
        dateOfBirthField = new JTextField(15);
        inputPanel.add(dateOfBirthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        inputPanel.add(genderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Caste:"), gbc);
        gbc.gridx = 1;
        casteField = new JTextField(15);
        inputPanel.add(casteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        inputPanel.add(new JLabel("Mobile No:"), gbc);
        gbc.gridx = 1;
        mobileNoField = new JTextField(15);
        inputPanel.add(mobileNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        inputPanel.add(new JLabel("House No:"), gbc);
        gbc.gridx = 1;
        houseNoField = new JTextField(15);
        inputPanel.add(houseNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        inputPanel.add(new JLabel("Street:"), gbc);
        gbc.gridx = 1;
        streetField = new JTextField(15);
        inputPanel.add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        inputPanel.add(new JLabel("Post:"), gbc);
        gbc.gridx = 1;
        postField = new JTextField(15);
        inputPanel.add(postField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        inputPanel.add(new JLabel("Village:"), gbc);
        gbc.gridx = 1;
        villageField = new JTextField(15);
        inputPanel.add(villageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        inputPanel.add(new JLabel("District:"), gbc);
        gbc.gridx = 1;
        districtField = new JTextField(15);
        inputPanel.add(districtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        inputPanel.add(new JLabel("State:"), gbc);
        gbc.gridx = 1;
        stateField = new JTextField(15);
        inputPanel.add(stateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        inputPanel.add(new JLabel("Pin:"), gbc);
        gbc.gridx = 1;
        pinField = new JTextField(15);
        inputPanel.add(pinField, gbc);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        addStudent = new JButton("Add Student");
        reset = new JButton("Reset");
        deleteRecord = new JButton("Delete Record");
        searchButton = new JButton("Search");
        generateReportButton = new JButton("Generate MS Access Report"); // New button for generating report

        addStudent.addActionListener(this);
        reset.addActionListener(this);
        deleteRecord.addActionListener(this);
        searchButton.addActionListener(this);
        generateReportButton.addActionListener(this); // Action listener for the new button

        buttonPanel.add(addStudent);
        buttonPanel.add(reset);
        buttonPanel.add(deleteRecord);
        buttonPanel.add(searchButton);
        buttonPanel.add(generateReportButton); // Add new button to the panel

        searchField = new JTextField(15);
        buttonPanel.add(new JLabel("Search by Hall Ticket No:"));
        buttonPanel.add(searchField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            handleAddStudent();
        } else if (e.getSource() == reset) {
            handleReset();
        } else if (e.getSource() == deleteRecord) {
            handleDeleteRecord();
        } else if (e.getSource() == searchButton) {
            handleSearch();
        } else if (e.getSource() == generateReportButton) {
            handleGenerateReport(); // Handle the new report generation
        }
    }

    private void handleAddStudent() {
        String hallTicketNo = hallTicketNoField.getText();
        String studentName = studentNameField.getText();
        String fatherName = fatherNameField.getText();
        String motherName = motherNameField.getText();
        String dateOfBirth = dateOfBirthField.getText();
        String gender = getSelectedGender();
        String caste = casteField.getText();
        String mobileNo = mobileNoField.getText();
        String houseNo = houseNoField.getText();
        String street = streetField.getText();
        String post = postField.getText();
        String village = villageField.getText();
        String district = districtField.getText();
        String state = stateField.getText();
        String pin = pinField.getText();

        if (hallTicketNo.isEmpty() || studentName.isEmpty() || fatherName.isEmpty() || motherName.isEmpty() || dateOfBirth.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateFormat.parse(dateOfBirth);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use dd-MM-yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert data into the database
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO students (hall_ticket_no, student_name, father_name, mother_name, date_of_birth, gender, caste, mobile_no, house_no, street, post, village, district, state, pin) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, hallTicketNo);
            pstmt.setString(2, studentName);
            pstmt.setString(3, fatherName);
            pstmt.setString(4, motherName);
            pstmt.setString(5, dateOfBirth);
            pstmt.setString(6, gender);
            pstmt.setString(7, caste);
            pstmt.setString(8, mobileNo);
            pstmt.setString(9, houseNo);
            pstmt.setString(10, street);
            pstmt.setString(11, post);
            pstmt.setString(12, village);
            pstmt.setString(13, district);
            pstmt.setString(14, state);
            pstmt.setString(15, pin);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully.");
                loadStudentDataFromDatabase();
                handleReset();
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void handleReset() {
        hallTicketNoField.setText("");
        studentNameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        dateOfBirthField.setText("");
        casteField.setText("");
        mobileNoField.setText("");
        houseNoField.setText("");
        streetField.setText("");
        postField.setText("");
        villageField.setText("");
        districtField.setText("");
        stateField.setText("");
        pinField.setText("");
        genderGroup.clearSelection();
    }

    private void handleDeleteRecord() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hallTicketNo = (String) studentTable.getValueAt(selectedRow, 0);

        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM students WHERE hall_ticket_no = ?");
            pstmt.setString(1, hallTicketNo);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully.");
                loadStudentDataFromDatabase();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSearch() {
        String hallTicketNo = searchField.getText();
        if (hallTicketNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Hall Ticket No to search.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM students WHERE hall_ticket_no = ?");
            pstmt.setString(1, hallTicketNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tableModel.setRowCount(0); // Clear existing data

                tableModel.addRow(new Object[]{
                        rs.getString("hall_ticket_no"),
                        rs.getString("student_name"),
                        rs.getString("father_name"),
                        rs.getString("mother_name"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("caste"),
                        rs.getString("mobile_no"),
                        rs.getString("house_no"),
                        rs.getString("street"),
                        rs.getString("post"),
                        rs.getString("village"),
                        rs.getString("district"),
                        rs.getString("state"),
                        rs.getString("pin")
                });
            } else {
                JOptionPane.showMessageDialog(this, "No record found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleGenerateReport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Report");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave + ".txt"))) {
                int rowCount = tableModel.getRowCount();
                int columnCount = tableModel.getColumnCount();

                for (int i = 0; i < columnCount; i++) {
                    writer.write(tableModel.getColumnName(i) + "\t");
                }
                writer.newLine();

                for (int i = 0; i < rowCount; i++) {
                    for (int j = 0; j < columnCount; j++) {
                        writer.write(tableModel.getValueAt(i, j).toString() + "\t");
                    }
                    writer.newLine();
                }
                writer.flush();

                JOptionPane.showMessageDialog(this, "Report generated successfully.");

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error generating report.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error connecting to database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentDataFromDatabase() {
        try {
            String query = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                    resultSet.getString("hall_ticket_no"),
                    resultSet.getString("student_name"),
                    resultSet.getString("father_name"),
                    resultSet.getString("mother_name"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("gender"),
                    resultSet.getString("caste"),
                    resultSet.getString("mobile_no"),
                    resultSet.getString("house_no"),
                    resultSet.getString("street"),
                    resultSet.getString("post"),
                    resultSet.getString("village"),
                    resultSet.getString("district"),
                    resultSet.getString("state"),
                    resultSet.getString("pin")
                });
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading student data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getSelectedGender() {
        if (maleRadioButton.isSelected()) {
            return "Male";
        } else if (femaleRadioButton.isSelected()) {
            return "Female";
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UniversityCollege().setVisible(true);
        });
    }
}
