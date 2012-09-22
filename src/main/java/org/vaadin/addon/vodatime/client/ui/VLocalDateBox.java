package org.vaadin.addon.vodatime.client.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.datepicker.client.DateBox;
import java.util.Date;

public class VLocalDateBox extends DateBox {

    public VLocalDateBox() {
        setFormat(new DateBox.DefaultFormat(
                DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM)));
    }
    
    public void setValueAsEpocStamp(Long l) {
        if(l == null) {
            setValue(null);
        } else {
            setValue(new Date(l), false);
        }
    }

}
