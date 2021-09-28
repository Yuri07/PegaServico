package com.compsol.appsol.pegaservico.oferecer.ui;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public interface OferecerView {

    void onRatingAdded(ServiceItem rating);
    void onRatingError(String error);

}
