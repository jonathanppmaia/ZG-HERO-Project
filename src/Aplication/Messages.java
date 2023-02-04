package Aplication;

public class Messages {

    public static String RESET_TEXT = "\u001B[0m";

    public static String RED_TEXT = "\u001B[31m";

    public static String GREEN_TEXT = "\u001B[32m";

    public static void mainMenu(int incompleteTaskCount, int completeTaskCount) {
        System.out.println("Menu");
        System.out.println("========================================\n");
        System.out.println("Você tem " + Messages.RED_TEXT
                + incompleteTaskCount + " tarefas a fazer "
                + Messages.RESET_TEXT + "e " + Messages.GREEN_TEXT
                + completeTaskCount + " Tarefas feitas\n" + Messages.RESET_TEXT);
        System.out.println("Opções do Menu: ");
        System.out.println("(1) - Mostrar Lista de Tarefas");
        System.out.println("(2) - Adicionar Nova Tarefa");
        System.out.println("(3) - Editar Tarefas");
        System.out.println("(4) - Salvar e Sair\n");
        System.out.println("========================================\n");
        System.out.println("Escolha uma das Opções [1 - 4]: ");
    }

    public static void listAllTasksMenu() {
        System.out.println("\nVisualidor de Tarefas");
        System.out.println("==================\n");
        System.out.println("Escolha uma Opção");
        System.out.println("(1) - Visualizar todas as Tarefas por Data"
                + Messages.RED_TEXT + "[ Volte e veja todas as Opções aperte [ENTER]]" + Messages.RESET_TEXT);
        System.out.println("(2) - Visualizar Tarefas por Prioridade");
        System.out.println("Escolha uma das Opções [1 - 2]: ");
    }

    public static void editTaskSelection() {
        System.out.println(GREEN_TEXT);
        System.out.print(">>> Digite um número da tarefa para EDITAR e pressione ENTER:: ");
        System.out.print(RESET_TEXT);
    }

    public static void editTaskMenu() {
        System.out.println("\nOpções para Editar Tarefas");
        System.out.println("======================\n");
        System.out.println("Escolha a opção:");
        System.out.println("(1) Editar tarefa selecionada");
        System.out.println("(2) Marcar tarefa como Completa");
        System.out.println("(3) Deletar tarefa selecionada");
        System.out.println("(4) Retornar ao menu principal"
                + Messages.RED_TEXT + " [Pressione ENTER]"+Messages.RESET_TEXT);
        System.out.print("\nPor favor selecione uma opção  [1-4]: ");
    }

    public static void  byeMessage() {
        System.out.println(GREEN_TEXT);
        System.out.println(">>> Todas as tarefas foram Salvas");
        System.out.println(RESET_TEXT);
    }

    public static void unknowMessage() {
        System.out.println(RED_TEXT);
        System.out.println(">>>Opção Incorreta: Por favor escolha outra opção");
        System.out.println(RESET_TEXT);
    }

    public static void showMessage(String message, boolean warning) {
        System.out.println(warning?RED_TEXT:GREEN_TEXT);
        System.out.println(">>> " + message);
        System.out.println(RESET_TEXT);
    }



    public static void separator (char charToPrint, int times){
        for (int index=0; index<times; index++)
            System.out.println(charToPrint);
        System.out.println();
    }


}



