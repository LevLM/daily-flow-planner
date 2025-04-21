package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Task;
import model.TaskPlanner;
import java.util.Date;

public class TaskForm extends JDialog {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JComboBox<Task.TaskPriority> priorityBox;
    private JComboBox<Task.TaskStatus> statusBox;
    private JSpinner dueDateSpinner;
    private JButton saveButton, cancelButton;
    private Task task;
    private TaskPlanner taskPlanner;

    public TaskForm(Frame owner, Task task) {
        super(owner, "Task Form", true);
        this.task = task;
        this.taskPlanner = ((MainWindow) owner).getTaskPlanner();

        setLayout(new BorderLayout());
        setSize(400, 300);
        setLocationRelativeTo(owner);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(descriptionArea));

        formPanel.add(new JLabel("Priority:"));
        priorityBox = new JComboBox<>(Task.TaskPriority.values());
        formPanel.add(priorityBox);

        formPanel.add(new JLabel("Status:"));
        statusBox = new JComboBox<>(Task.TaskStatus.values());
        formPanel.add(statusBox);

        formPanel.add(new JLabel("Due Date:"));
        dueDateSpinner = new JSpinner(new SpinnerDateModel());
        formPanel.add(dueDateSpinner);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        if (task != null) {
            loadTaskData();
        }

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTask();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void loadTaskData() {
        titleField.setText(task.getTitle());
        descriptionArea.setText(task.getDescription());
        priorityBox.setSelectedItem(task.getPriority());
        statusBox.setSelectedItem(task.getStatus());
        dueDateSpinner.setValue(task.getDueDate());
    }

    private void saveTask() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        Date dueDate = (Date) dueDateSpinner.getValue();
        Task.TaskPriority priority = (Task.TaskPriority) priorityBox.getSelectedItem();
        Task.TaskStatus status = (Task.TaskStatus) statusBox.getSelectedItem();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (task == null) {
            task = new Task(title, description, dueDate, priority, status);
            taskPlanner.addTask(task);
        } else {
            task.setTitle(title);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setPriority(priority);
            task.setStatus(status);
        }

        ((MainWindow) getOwner()).refreshTaskList();
        dispose();
    }
}