package Aplication;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.zip.DataFormatException;

public class Task implements Serializable {

    private String name;

    private String description;

    private LocalDate date;

    private String priority;

    private String category;

    private String Status;

    private boolean complete;

    private String status;


    public Task(String name, String description, LocalDate date, String priority, String category, String status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.priority = priority;
        this.category = category;
        this.Status = status;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws  NullPointerException{
        if(name.trim().equals("") || name == null){
            throw new NullPointerException("ATENÇÃO - Nome da Tarefa Obrigatória");
        }
        this.name = name.trim();
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws DataFormatException {
        if(date.compareTo(LocalDate.now())<0) {
            throw new DateTimeException("Por gentileza colocar data futura");
        }
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = LocalDate.parse(date.format(formattedDate));
    }


    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {return  this.status;}

    public void setStatus(String status) {
        this.Status = status.trim();
    }

    public boolean isComplete() {
        return this.complete;
    }

    public boolean markInComplete() {
        this.complete = false;
        return this.complete;
    }

    public boolean markCompleted() {
        this.complete = true;
        return this.complete;
    }


    public String formattedStringOfTask() {
        return "task[" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", priority=" + priority +
                ", category='" + category + '\'' +
                ", Status=" + Status +
                ", complete=" + (complete?"Completo":"Não completado") +
                "]";
    }
}

