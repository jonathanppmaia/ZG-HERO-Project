package Aplication;

import java.util.Arrays;
import java.util.Scanner;



public class Main {

    public static String filename = "tasks.obj";

    public static void main(String[] args) {


        TodoList todoList = new TodoList();

        String menuChoice = "-25";

        try {
            Scanner input = new Scanner(System.in);

            todoList.readFromFile(filename);


            Aplication.Messages.showMessage(" Bem-vindo ao TodoList", false);

            while (!menuChoice.equals("4")) {
                Aplication.Messages.mainMenu(todoList.notCompletedCount(), todoList.completedCount());
                menuChoice = input.nextLine();

                switch (menuChoice) {
                    case "1":
                        Aplication.Messages.listAllTasksMenu();
                        todoList.lisAllTasks(input.nextLine());

                        break;
                    case "2":
                        todoList.readTaskFromUser();
                        break;
                    case "3":
                        todoList.listAllTasksWithIndex();
                        Aplication.Messages.editTaskSelection();
                        todoList.editTask(input.nextLine());
                        break;
                    case "4":
                        break;
                    default:
                        Aplication.Messages.unknowMessage();
                }
            }

            todoList.saveToFile(filename);
            Aplication.Messages.byeMessage();

        } catch (Exception e) {
            Aplication.Messages.showMessage("Exceção de Dados", true);
            System.out.println("Tentando Gravar os dados de todas as tarefas no arquivo !!");
            todoList.saveToFile(filename);
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}


