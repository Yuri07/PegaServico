package com.compsol.appsol.pegaservico.entities;

public class ServiceItemBuilder {

    private String serviceId;
    private String email;
    private String nome;
    private String data;
    private String entrada;
    private int periodo;
    private double valor;
    private int status;
    private String urlPhotoUser;

    public ServiceItemBuilder serviceID(String serviceId){
        this.serviceId = serviceId;
        return this;
    }

    public ServiceItemBuilder email(String email) {
        this.email = email;
        return this;
    }

    public ServiceItemBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ServiceItemBuilder data(String data) {
        this.data = data;
        return this;
    }

    public ServiceItemBuilder entrada(String entrada) {
        this.entrada = entrada;
        return this;
    }

    public ServiceItemBuilder periodo(int periodo) {
        this.periodo = periodo;
        return this;
    }

    public ServiceItemBuilder valor(double valor) {
        this.valor = valor;
        return this;
    }

    public ServiceItemBuilder status(int status) {
        this.status = status;
        return this;
    }

    public ServiceItemBuilder urlPhotoUser(String urlPhotoUser) {
        this.urlPhotoUser = urlPhotoUser;
        return this;
    }

    public ServiceItem build(){
        return new ServiceItem(email, nome, data, entrada, periodo,
                                                valor, status, urlPhotoUser);
    }

}
