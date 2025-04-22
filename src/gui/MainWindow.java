package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

import model.Task;
import model.TaskPlanner;

public class MainWindow extends JFrame {
	private JList<Task> taskList;
	private DefaultListModel<Task> taskListModel;

	private JButton addButton, editButton, deleteButton;
	private TaskPlanner taskPlanner;

	public MainWindow() {
		setTitle("DailyFlowPlanner");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		taskPlanner = new TaskPlanner();
		initComponents();
	}

	private void initComponents() {

		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);

		// Устанавливаем кастомный рендерер для отображения задач
		taskList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Task) {
					setText(((Task) value).toString()); // Отображаем задачу как строку
				}
				return this;
			}
		});

		JScrollPane scrollPane = new JScrollPane(taskList);

		addButton = new JButton("Add Task");
		editButton = new JButton("Edit Task");
		deleteButton = new JButton("Delete Task");

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskForm taskForm = new TaskForm(MainWindow.this, null);
				taskForm.setVisible(true);
				refreshTaskList();
			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = taskList.getSelectedIndex();
				if (selectedIndex != -1) {
					Task selectedTask = taskListModel.getElementAt(selectedIndex);
					TaskForm taskForm = new TaskForm(MainWindow.this, selectedTask);
					taskForm.setVisible(true);
					refreshTaskList();
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = taskList.getSelectedIndex();
				if (selectedIndex != -1) {
					Task selectedTask = taskListModel.getElementAt(selectedIndex);
					taskPlanner.removeTask(selectedTask);
					refreshTaskList();
				}
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);

		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public void refreshTaskList() {
		taskListModel.clear();
		taskPlanner.sortTasks();

		for (Task task : taskPlanner.getTasks()) {
			taskListModel.addElement(task);
		}
	}

	public TaskPlanner getTaskPlanner() {
		return taskPlanner;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow mainWindow = new MainWindow();
			mainWindow.setVisible(true);
		});
	}
}
