package com.compsol.appsol.pegaservico.pegar.ui;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public interface PegarView {

    void onServiceReceived(ServiceItem service);
    void onServiceReceivedError(String error);

    void onServiceClickedToCatch(ServiceItem service);

}
