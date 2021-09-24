package com.compsol.appsol.pegaservico.login;

public interface LoginRepository {
    void signUp(final String email, final String password);
    void signIn(String email, String password);
}
