
import javax.swing.*;
import java.awt.*;

public class ToDoAppGUI extends JFrame {
    private final DefaultListModel<Task> listModel = new DefaultListModel<>();
    private final JList<Task> taskList = new JList<>(listModel);
    private final JTextField taskInput = new JTextField(20);
    private final TaskManager taskManager = new TaskManager();


    public ToDoAppGUI() {
        super("To-Do List");

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        inputPanel.add(taskInput);
        inputPanel.add(addButton);

        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel controlPanel = new JPanel();
        JButton removeButton = new JButton("Remove Selected");
        JButton exitButton = new JButton("Exit");
        controlPanel.add(removeButton);
        controlPanel.add(exitButton);
        JButton editButton = new JButton("Edit Selected");
        JButton toggleCompleteButton = new JButton("Toggle Complete");
        controlPanel.add(editButton);
        controlPanel.add(toggleCompleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);



        editButton.addActionListener(e -> {
                    int selectedIndex = taskList.getSelectedIndex();
                    if (selectedIndex == -1) {
                        JOptionPane.showMessageDialog(this, "Select a task to edit.");
                        return;
                    }
                    Task selectedTask = listModel.getElementAt(selectedIndex);
                    String newDescription = JOptionPane.showInputDialog(this, "Edit Task Description:", selectedTask.getDescription());
                    if (newDescription != null && !newDescription.trim().isEmpty()) {
                        taskManager.updateTaskDescription(selectedIndex, newDescription);
                        listModel.set(selectedIndex, taskManager.getTasks().get(selectedIndex));
                    }
                });

        toggleCompleteButton.addActionListener(e-> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select a task to toggle completion.");
                return;
            }
            Task selectedTask = listModel.getElementAt(selectedIndex);
            boolean newStatus = !selectedTask.isCompleted();
            taskManager.setTaskCompleted(selectedIndex, newStatus);
            listModel.set(selectedIndex, taskManager.getTasks().get(selectedIndex));
        });
        addButton.addActionListener(e -> {
            String taskText = taskInput.getText().trim();
            if (taskText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Task cannot be empty!");
                return;
            }
            Task newTask = new Task(taskText);
            taskManager.addTask(newTask);
            listModel.addElement(newTask);
            taskInput.setText("");
        });

        removeButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "Select a task to remove.");
                return;
            }
            taskManager.deleteTask(selectedIndex);
            listModel.remove(selectedIndex);
        });

        exitButton.addActionListener(e -> System.exit(0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoAppGUI::new);
    }
}
