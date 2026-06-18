import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SchedulerGUI extends JFrame {

    private SchedulerSystem system;
    private JTextArea displayArea;

    private final Color BLACK_BG = new Color(15, 15, 15);
    private final Color DARK_PANEL = new Color(28, 28, 28);
    private final Color MID_GREY = new Color(45, 45, 45);
    private final Color GOLD = new Color(212, 175, 55);
    private final Color GOLD_HOVER = new Color(232, 195, 70);
    private final Color TEXT_LIGHT = new Color(240, 240, 240);
    private final Color TEXT_MUTED = new Color(170, 170, 170);

    public SchedulerGUI() {
        system = new SchedulerSystem();

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Hospital Operation Scheduler");
        setSize(1000, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(20, 20));
        root.setBackground(BLACK_BG);
        root.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(root);

        // HEADER
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(BLACK_BG);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Hospital Operation Scheduler");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(GOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Manage professionals, appointments and records");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(TEXT_MUTED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(6));
        headerPanel.add(subtitleLabel);

        root.add(headerPanel, BorderLayout.NORTH);

        // CENTER
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        centerPanel.setBackground(BLACK_BG);

        // LEFT PANEL
        JPanel actionsPanel = createSectionPanel("Main Actions");
        JPanel actionsGrid = new JPanel(new GridLayout(3, 2, 12, 12));
        actionsGrid.setBackground(DARK_PANEL);

        JButton addProfessionalBtn = createStyledButton("Add Professional");
        JButton viewProfessionalBtn = createStyledButton("View Professionals");
        JButton addAppointmentBtn = createStyledButton("Add Appointment");
        JButton viewDiaryBtn = createStyledButton("View Diary");
        JButton deleteAppointmentBtn = createStyledButton("Delete Appointment");
        JButton clearScreenBtn = createStyledButton("Clear Display");

        actionsGrid.add(addProfessionalBtn);
        actionsGrid.add(viewProfessionalBtn);
        actionsGrid.add(addAppointmentBtn);
        actionsGrid.add(viewDiaryBtn);
        actionsGrid.add(deleteAppointmentBtn);
        actionsGrid.add(clearScreenBtn);

        actionsPanel.add(actionsGrid, BorderLayout.CENTER);

        // RIGHT PANEL = DISPLAY AREA
        JPanel displayPanel = createSectionPanel("System Display");
        JPanel displayContent = new JPanel(new BorderLayout(15, 15));
        displayContent.setBackground(DARK_PANEL);

        JLabel displayTitle = new JLabel("Live Records Viewer");
        displayTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        displayTitle.setForeground(GOLD);
        displayTitle.setBorder(new EmptyBorder(0, 0, 5, 0));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setBackground(MID_GREY);
        displayArea.setForeground(TEXT_LIGHT);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setBorder(new EmptyBorder(20, 20, 20, 20));
        displayArea.setText(
                "Welcome to the Hospital Operation Scheduler.\n\n" +
                "Use View Professionals to see all professionals.\n" +
                "Use View Diary to see one professional and the patients assigned to them.\n"
        );

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(MID_GREY);

        JPanel saveLoadPanel = new JPanel(new GridLayout(1, 2, 12, 12));
        saveLoadPanel.setBackground(DARK_PANEL);

        JButton saveBtn = createStyledButton("Save Data");
        JButton loadBtn = createStyledButton("Load Data");

        saveLoadPanel.add(saveBtn);
        saveLoadPanel.add(loadBtn);

        displayContent.add(displayTitle, BorderLayout.NORTH);
        displayContent.add(scrollPane, BorderLayout.CENTER);
        displayContent.add(saveLoadPanel, BorderLayout.SOUTH);

        displayPanel.add(displayContent, BorderLayout.CENTER);

        centerPanel.add(actionsPanel);
        centerPanel.add(displayPanel);

        root.add(centerPanel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("Premium hospital scheduler interface");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(TEXT_MUTED);
        root.add(footerLabel, BorderLayout.SOUTH);

        // ACTIONS

        addProfessionalBtn.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter Name");
            if (name == null || name.trim().isEmpty()) return;

            String profession = JOptionPane.showInputDialog(this, "Enter Profession");
            if (profession == null || profession.trim().isEmpty()) return;

            String location = JOptionPane.showInputDialog(this, "Enter Location");
            if (location == null || location.trim().isEmpty()) return;

            system.addProfessional(name, profession, location);
            displayArea.setText("Professional added successfully:\n\nName: " + name +
                    "\nProfession: " + profession +
                    "\nLocation: " + location);
        });

        viewProfessionalBtn.addActionListener(e -> {
            StringBuilder result = new StringBuilder();
            result.append("ALL HEALTH PROFESSIONALS\n");
            result.append("====================================\n\n");

            boolean found = false;

            for (int i = 1; i <= 100; i++) {
                if (system.professionalExists(i)) {
                    result.append(system.getProfessional(i)).append("\n\n");
                    found = true;
                }
            }

            if (!found) {
                result.append("No professionals found.");
            }

            displayArea.setText(result.toString());
            displayArea.setCaretPosition(0);
        });

        addAppointmentBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Professional ID"));

                if (!system.professionalExists(id)) {
                    JOptionPane.showMessageDialog(this, "Professional not found");
                    return;
                }

                String patientName = JOptionPane.showInputDialog(this, "Patient Name");
                if (patientName == null || patientName.trim().isEmpty()) return;

                int patientAge = Integer.parseInt(JOptionPane.showInputDialog(this, "Patient Age"));
                String date = JOptionPane.showInputDialog(this, "Date");
                String start = JOptionPane.showInputDialog(this, "Start Time");
                String end = JOptionPane.showInputDialog(this, "End Time");
                String treatment = JOptionPane.showInputDialog(this, "Treatment");

                system.getProfessional(id).getDiary().addAppointment(
                        new Appointment(patientName, patientAge, date, start, end, treatment)
                );

                displayArea.setText(
                        "Appointment added successfully.\n\n" +
                        "Professional ID: " + id + "\n" +
                        "Patient Name: " + patientName + "\n" +
                        "Patient Age: " + patientAge + "\n" +
                        "Date: " + date + "\n" +
                        "Start Time: " + start + "\n" +
                        "End Time: " + end + "\n" +
                        "Treatment: " + treatment
                );
                displayArea.setCaretPosition(0);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number entered");
            }
        });

        viewDiaryBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Professional ID"));

                if (!system.professionalExists(id)) {
                    JOptionPane.showMessageDialog(this, "Professional not found");
                    return;
                }

                StringBuilder diaryInfo = new StringBuilder();
                diaryInfo.append("PROFESSIONAL DIARY\n");
                diaryInfo.append("====================================\n\n");
                diaryInfo.append("PROFESSIONAL DETAILS\n");
                diaryInfo.append("------------------------------------\n");
                diaryInfo.append(system.getProfessional(id)).append("\n\n");
                diaryInfo.append("PATIENTS / APPOINTMENTS\n");
                diaryInfo.append("------------------------------------\n");
                diaryInfo.append(system.getProfessional(id).getDiary().getAppointmentsText());

                displayArea.setText(diaryInfo.toString());
                displayArea.setCaretPosition(0);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID entered");
            }
        });

        deleteAppointmentBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Professional ID"));

                if (!system.professionalExists(id)) {
                    JOptionPane.showMessageDialog(this, "Professional not found");
                    return;
                }

                int index = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Appointment Number"));
                system.getProfessional(id).getDiary().deleteAppointment(index - 1);

                displayArea.setText("Appointment " + index + " deleted successfully for professional ID " + id + ".");
                displayArea.setCaretPosition(0);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number entered");
            }
        });

        clearScreenBtn.addActionListener(e -> {
            displayArea.setText("Display cleared.");
        });

        saveBtn.addActionListener(e -> {
            system.saveData("hospital.dat");
            displayArea.setText("Data saved successfully to hospital.dat");
        });

        loadBtn.addActionListener(e -> {
            system.loadData("hospital.dat");
            displayArea.setText("Data loaded successfully from hospital.dat");
        });
    }

    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARK_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(GOLD, 1),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(GOLD);
        titleLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        panel.add(titleLabel, BorderLayout.NORTH);
        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new RoundedButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(155, 40));
        button.setBackground(GOLD);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(GOLD_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(GOLD);
            }
        });

        return button;
    }

    private static class RoundedButton extends JButton {
        public RoundedButton(String text) {
            super(text);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 22, 22);

            FontMetrics fm = g2.getFontMetrics();
            Rectangle stringBounds = fm.getStringBounds(getText(), g2).getBounds();

            int textX = (getWidth() - stringBounds.width) / 2;
            int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();

            g2.setColor(getForeground());
            g2.setFont(getFont());
            g2.drawString(getText(), textX, textY);

            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SchedulerGUI().setVisible(true));
    }
}