package com.compsol.appsol.pegaservico.entities;

import java.util.Map;

public class User {

    private String email;
    private String nome;
    private String sobreNome;
    private int status;
    private double latitude;
    private double longitude;
    private String urlPhotoUser;
    private Map<String, Integer> historicChats;

    private double averageRating;
    private int totalRatings;

    private int count5Stars;
    private int count4Stars;
    private int count3Stars;
    private int count2Stars;
    private int count1Stars;

    public final static String USER_NAME_DEFAULT = "Usuário";
    public final static int OFFLINE = 0;
    public final static int ONLINE = 1;
    public final static int LOOKING_FOR_CAR = 2;
    public final static int WAITING_CAR = 3;
    public final static int IN_CAR = 4;

    public User(){ }

    public User(String email, int status, Map<String, Integer> historicChats){
        this.email = email;
        this.status = status;
        this.historicChats = historicChats;
        this.nome = "Usuario";
        this.urlPhotoUser = "defalut";
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

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Map<String, Integer> getHistoricChats() {
        return historicChats;
    }

    public void setHistoricChats(Map<String, Integer> historicChats) {
        this.historicChats = historicChats;
    }

    public String getUrlPhotoUser() {
        return urlPhotoUser;
    }

    public void setUrlPhotoUser(String urlPhotoUser) {
        this.urlPhotoUser = urlPhotoUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public int getCount5Stars() {
        return count5Stars;
    }

    public void setCount5Stars(int count5Stars) {
        this.count5Stars = count5Stars;
    }

    public int getCount4Stars() {
        return count4Stars;
    }

    public void setCount4Stars(int count4Stars) {
        this.count4Stars = count4Stars;
    }

    public int getCount3Stars() {
        return count3Stars;
    }

    public void setCount3Stars(int count3Stars) {
        this.count3Stars = count3Stars;
    }

    public int getCount2Stars() {
        return count2Stars;
    }

    public void setCount2Stars(int count2Stars) {
        this.count2Stars = count2Stars;
    }

    public int getCount1Stars() {
        return count1Stars;
    }

    public void setCount1Stars(int count1Stars) {
        this.count1Stars = count1Stars;
    }

}
