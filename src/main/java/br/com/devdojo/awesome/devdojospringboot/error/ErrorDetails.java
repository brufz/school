package br.com.devdojo.awesome.devdojospringboot.error;

public class ErrorDetails {

    //aqui fica padronizado o que eu quero que apareça na mensagem de erro
    private String title;
    private int status;
    private String details;
    private long timestamp;
    private String developerMessage;


    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
