package com.compsol.appsol.pegaservico.entities;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ServiceItem {

    private String serviceId;
    private String email;
    private String nome;
    private String data;
    private String entrada;
    private int periodo;
    private double valor;
    private String status;
    private String urlPhotoUser;
    private String receiverEmail;

    public final static String waitingAcceptStatus = "Esperando";
    public final static String acceptedStatus = "Aceito";
    public final static String confirmedStatus = "Confirmado";
    public final static String inProgressStatus = "Em Progresso";
    public final static String concludedStatus = "Concluído";

    public ServiceItem() {

    }

    public ServiceItem(String email, String nome, String data, String entrada, int periodo,
                                                double valor, String status, String urlPhotoUser) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrlPhotoUser() {
        return urlPhotoUser;
    }

    public void setUrlPhotoUser(String urlPhotoUser) {
        this.urlPhotoUser = urlPhotoUser;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("serviceId", serviceId);
        result.put("email", email);
        result.put("nome", nome);
        result.put("data", data);
        result.put("entrada", entrada);
        result.put("periodo", periodo);
        result.put("valor", valor);
        result.put("status", status);
        result.put("urlPhotoUser", urlPhotoUser);
        result.put("receiverEmail", receiverEmail);

        return result;
    }

}
