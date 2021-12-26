package com.compsol.appsol.pegaservico.oferecer.ui;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public interface OferecerView {

    void onServiceReceived(ServiceItem service);
    void onServiceReceivedError(String error);

}
