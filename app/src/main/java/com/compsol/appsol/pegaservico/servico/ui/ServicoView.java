package com.compsol.appsol.pegaservico.servico.ui;

import com.compsol.appsol.pegaservico.entities.User;

public interface ServicoView {
    void onSuccessToGetDataUser(User currentUser);

    void onFailedToGetDateUser();

    void onServiceConfirmedError();

    void onServiceConfirmed();
}
