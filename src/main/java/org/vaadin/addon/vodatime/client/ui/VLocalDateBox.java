package org.vaadin.addon.vodatime.client.ui;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.datepicker.client.DateBox;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.VConsole;

public class VLocalDateBox extends DateBox implements Paintable,
        ValueChangeHandler<Date> {

    private ApplicationConnection client;

    public VLocalDateBox() {
        addValueChangeHandler(this);
        setFormat(new DateBox.DefaultFormat(
                DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM)));
    }

    public void updateFromUIDL(final UIDL uidl,
            final ApplicationConnection client) {
        this.client = client;
        VConsole.log("Updating VLocalDateBox");
        if (client.updateComponent(this, uidl, true)) {
            return;
        }

        if (uidl.hasVariable("v")) {
            long longVariable = uidl.getLongVariable("v");
            Date date = new Date(longVariable);
            setValue(date, false);
        } else {
            setValue(null, false);
        }
    }

    public void onValueChange(ValueChangeEvent<Date> event) {
        Date value = event.getValue();
        client.updateVariable(client.getPid(this), "v", value.getTime(), true);
    }

}
