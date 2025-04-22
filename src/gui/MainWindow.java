package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import model.Task;
import model.TaskPlanner;

public class MainWindow extends JFrame {
	private JList<Task> taskList;
	private DefaultListModel<Task> taskListModel;
	private JLabel filterLabel;

	private JButton addButton, editButton, deleteButton, todayButton, completedButton, activeButton, showAllButton;
	private TaskPlanner taskPlanner;

	public MainWindow() {
		setTitle("DailyFlowPlanner");
		setSize(900, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		taskPlanner = new TaskPlanner();
		initComponents();
		
	}

	private void initComponents() {

		taskListModel = new DefaultListModel<>();
		taskList = new JList<>(taskListModel);

		taskList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Task) {
					setText(((Task) value).toString()); 
				}
				return this;
			}
		});

		JScrollPane scrollPane = new JScrollPane(taskList);

		showAllButton = new JButton("Show All Tasks");
		addButton = new JButton("Add Task");
		editButton = new JButton("Edit Task");
		deleteButton = new JButton("Delete Task");
		todayButton = new JButton("Show Today Tasks");
		completedButton = new JButton("Show Completed Tasks");
		activeButton = new JButton("Show Active Tasks");
		
		filterLabel = new JLabel("All Tasks");
		filterLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(filterLabel, BorderLayout.NORTH);
		
		completedButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        showCompletedTasks();
		    }
		});

		activeButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        showActiveTasks();
		    }
		});
		
		todayButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        showTodayTasks();
		    }
		});

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
		
		showAllButton.addActionListener(e -> refreshTaskList());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(showAllButton);
		buttonPanel.add(todayButton);
		buttonPanel.add(activeButton);
		
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(deleteButton);
		
		buttonPanel.add(completedButton);
		

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
		filterLabel.setText("All Tasks");
	}

	public TaskPlanner getTaskPlanner() {
		return taskPlanner;
	}
	
	private void showTodayTasks() {
	    taskListModel.clear();
	    taskPlanner.sortTasks();
	    Date today = new Date();
	    for (Task task : taskPlanner.getTasks()) {
	        if (isSameDay(task.getDueDate(), today)) {
	            taskListModel.addElement(task);
	        }
	    }
	    filterLabel.setText("Today's Tasks");
	}
	
	private boolean isSameDay(Date date1, Date date2) {
	    Calendar cal1 = Calendar.getInstance();
	    Calendar cal2 = Calendar.getInstance();
	    cal1.setTime(date1);
	    cal2.setTime(date2);
	    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	           cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
	}

	private void showCompletedTasks() {
	    taskListModel.clear();
	    for (Task task : taskPlanner.getTasks()) {
	        if (task.getStatus() == Task.TaskStatus.COMPLETED) {
	            taskListModel.addElement(task);
	        }
	    }
	    filterLabel.setText("Completed Tasks");
	}

	private void showActiveTasks() {
	    taskListModel.clear();
	    for (Task task : taskPlanner.getTasks()) {
	        if (task.getStatus() == Task.TaskStatus.PENDING || task.getStatus() == Task.TaskStatus.IN_PROGRESS) {
	            taskListModel.addElement(task);
	        }
	    }
	    filterLabel.setText("Active Tasks");
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow mainWindow = new MainWindow();
			mainWindow.setVisible(true);
		});
	}
}
