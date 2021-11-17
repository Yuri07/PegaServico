package com.compsol.appsol.pegaservico.entities;

public class ServiceItem {

    private String serviceId;
    private String email;
    private String nome;
    private String data;
    private String entrada;
    private int periodo;
    private double valor;
    private int status;
    private String urlPhotoUser;

    public final static int waitingAcceptStatus = 101;
    public final static int acceptedStatus = 102;
    public final static int confirmedStatus = 103;
    public final static int inProgressStatus = 104;
    public final static int concludedStatus = 105;

    public ServiceItem() {

    }

    public ServiceItem(String email, String nome, String data, String entrada, int periodo,
                                                double valor, int status, String urlPhotoUser) {
        this.email = email;
        this.nome = nome;
        this.data = data;
        this.entrada = entrada;
        this.periodo = periodo;
        this.valor = valor;
        this.status = status;
        this.urlPhotoUser = urlPhotoUser;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrlPhotoUser() {
        return urlPhotoUser;
    }

    public void setUrlPhotoUser(String urlPhotoUser) {
        this.urlPhotoUser = urlPhotoUser;
    }
}
