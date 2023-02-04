package Aplication;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TodoList {

    private ArrayList<Task> taskList;

    public TodoList() {
        taskList = new ArrayList<>();
    }

    public void addTask(String name, String description, LocalDate date, String priority, String category, String status) {
        this.taskList.add(new Task(name,description,date,priority,category,status));

    }


    public void readTaskFromUser() {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println(Messages.GREEN_TEXT + "Adicione uma Tarefa" + Messages.RESET_TEXT);
            System.out.println(">>> Nome da Tarefa: ");
            String name = scan.nextLine();
            System.out.println(">>> Descrição da Tarefa: ");
            String description = scan.nextLine();
            System.out.println(">>> Data da Tarefa [ yyyy-MM-dd}: ");
            LocalDate date = LocalDate.parse(scan.nextLine());
            System.out.println(">>> Prioridade da Tarefa: ");
            String priority = scan.nextLine();
            System.out.println(">>> Categoria da Tarefa: ");
            String category = scan.nextLine();
            System.out.println(">>> Status da Tarefa: ");
            String status = scan.nextLine();

            this.taskList.add(new Task(name, description, date, priority, category, status));
            Messages.showMessage("Tarefa Adicionada com Sucesso", false);

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(), true);
        }

    }

    public boolean readTaskFromUserUpdate(Task task) {

        Scanner scan = new Scanner(System.in);
        boolean isTaskUpdate = false;

        try {
            System.out.println(Messages.GREEN_TEXT + "Insira os detalhes para a Alteração da Tarefa:" + "\n Se você não deseja alterar nenhuma infomação, aperte ENTER" + Messages.RESET_TEXT);

            System.out.println(">>> Nome da Tarefa: ");
            String name = scan.nextLine();
            if (!name.trim().equals("")) {
                task.setName(name);
                isTaskUpdate = true;
            }

            System.out.println(">>> Descrição da Tarefa: ");
            String description = scan.nextLine();
            if (!description.trim().equals("")) {
                task.setDescription(description);
                isTaskUpdate = true;
            }

            System.out.println(">>> Data da Tarefa [ 2023-02-31}: ");
            String date = scan.nextLine();
            if (!date.trim().equals("")) {
                task.setDate(LocalDate.parse(date));
                isTaskUpdate = true;
            }

            System.out.println(">>> Prioridade da Tarefa:[1 - 5] ");
            String priority = scan.nextLine();
            if (!priority.trim().equals("")) {
                task.setPriority(priority);
                isTaskUpdate = true;
            }

            System.out.println(">>> Status da Tarefa: [a fazer, fazendo, concluído]");
            String status = scan.nextLine();
            if (!(status.trim().equals("") || status == null)) {
                task.setStatus(status);
                isTaskUpdate = true;
            }

            Messages.showMessage("A tarefa " + (isTaskUpdate ? "foi alterada com sucesso" : "Não foi ALterada") + ": retornar ao menu inicial", false);

            return true;

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(), true);
            return false;
        }
    }

    public void listAllTasksWithIndex() {
        String displayFormat = "%-2s% -35s% -35s% -10s% -10s% -10s%  %-10s %-12s";

        if (taskList.size() > 0) {
            System.out.printf((displayFormat) + "%n", "NOME", "DESCRIÇÃO", "DATA", "PRIORIDADE", "CATEGORIA", "STATUS", "COMPLETO");
            System.out.println(String.format(displayFormat,"%n","====","=========","====", "==========","=========", "======", "========"));
        } else {
            System.out.println(Messages.RED_TEXT + "Nenhuma Tarefa para Listar" + Messages.RESET_TEXT);
        }

        taskList.stream()
                .forEach(Task -> System.out.printf((displayFormat) + "%n",
                        taskList.indexOf(Task) +1,
                        Task.getName(),
                        Task.getDescription(),
                        Task.getDate(),
                        Task.getPriority(),
                        Task.getCategory(),
                        Task.getStatus(),
                        (Task.isComplete() ? "YES" : "NO")
                ));
    }

    public void lisAllTasks(String sortBy) {
        Messages.separator('=', 75);
        System.out.println(
                "Total de Tarefas = " + taskList.size() +
                        "\t\t (Completas = " + completedCount() + "\t\t" +
                        Messages.RED_TEXT + " Não Completas = " + notCompletedCount() + Messages.RESET_TEXT +
                        " )");
        Messages.separator('=', 75);

        if (sortBy.equals("2")) {
            String displayFormat = "%-2s% -10s% -35s% -35s% -10s%  -10s%  %-10s %-12s";

            if (taskList.size()> 0) {
                System.out.println(String.format(displayFormat, "%n", "PRIORIDADE", "NOME", "DESCRIÇÃO", "DATA",  "CATEGORIA", "STATUS", "COMPLETO"));
                System.out.printf((displayFormat) + "%n","==========", "====","=========","====", "=========", "======", "========");
            } else {
                System.out.println(Messages.RED_TEXT + "Nenhuma Tarefa para Listar" + Messages.RESET_TEXT);
            }

            taskList.stream()
                    .sorted(Comparator.comparing(Task::getPriority))
                    .forEach(Task -> System.out.printf((displayFormat) + "%n",
                            Task.getPriority(),
                            Task.getName(),
                            Task.getDescription(),
                            Task.getDate(),
                            Task.getCategory(),
                            Task.getStatus(),
                            (Task.isComplete() ? "YES" : "NO")
                    ));
        }
        else {
            String displayFormat = "%-10s %-35s %-20s %-10s";

            if (taskList.size() > 0) {
                System.out.printf((displayFormat) + "%n", "NAME", "DESCRITION", "DATE", "PRIORITY", "CATEGORY", "STATUS", "COMPLETED");
                System.out.printf((displayFormat) + "%n", "====", "==========", "====", "========", "========", "======", "=========");
            } else {
                System.out.println(Messages.RED_TEXT + "Nenhuma Tarefa para Mostrar" + Messages.RESET_TEXT);
            }

            taskList.stream()
                    .sorted(Comparator.comparing(Task::getDate))
                    .forEach(Task -> System.out.printf((displayFormat) + "%n", Task.getDate(),
                            Task.getName(),
                            Task.getDescription(),
                            Task.getPriority(),
                            Task.getCategory(),
                            Task.getStatus(),
                            (Task.isComplete() ? "YES" : "NO")
                    ));
        }
    }

    public void editTask(String selectedTask) throws NullPointerException {
        try {
            // checking if the task number is given and empty string or null
            if (selectedTask.trim().equals("")) {
                throw new NullPointerException("Tarefas Vazias: Retornar ao Menu Principal");
            }

            int taskIndex = Integer.parseInt(selectedTask) - 1;
            if (taskIndex < 0 || taskIndex > taskList.size()) {
                throw new ArrayIndexOutOfBoundsException("Número da Tarefa não econtrado: Retornar ao Menu Principal");
            }

            Task task = taskList.get(taskIndex);

            Messages.showMessage("Tarefa Num " + selectedTask + "  está selecionada:" + task.formattedStringOfTask(), false);

            Messages.editTaskMenu();
            Scanner scan = new Scanner(System.in);
            String editChoice = scan.nextLine();
            switch (editChoice) {
                case "1":
                    readTaskFromUserUpdate(task);
                    break;
                case "2":
                    task.markCompleted();
                    Messages.showMessage("Tarefa Numero " + selectedTask + " está Concluida: RRetorne ao Menu Principal", false);
                    break;
                case "3":
                    taskList.remove(task);
                    Messages.showMessage("Tarefa Num " + selectedTask + " foi deletada: Retorne ao Menu Principal", true);
                    break;
                default:
                    Messages.showMessage("Retorne ao Menu Principal", true);
            }
        } catch (Exception e) {
            Messages.showMessage(e.getMessage(), true);
        }
    }
    public int completedCount() {
        return (int) taskList.stream()
                .filter(Task::isComplete)
                .count();
    }


    public int notCompletedCount() {
        return (int) taskList.stream()
                .filter(Task -> !Task.isComplete())
                .count();
    }


    public boolean readFromFile(String filename) {
        boolean status = false;

        try {
            if (!Files.isReadable(Paths.get(filename))) {
                Messages.showMessage("O arquivo " + filename + " não existe", true);
                return false;
            }

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            taskList = (ArrayList<Task>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return true;

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(), true);
            return false;
        }
    }
    public void saveToFile(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(taskList);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {
            Messages.showMessage(e.getMessage(), true);
        }
    }

}