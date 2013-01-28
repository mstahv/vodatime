package org.vaadin.addon.vodatime;

import java.util.Date;

import org.joda.time.LocalDate;
import org.vaadin.addon.vodatime.client.vaadin.LocalDateBoxRpc;
import org.vaadin.addon.vodatime.client.vaadin.LocalDateBoxState;

import com.vaadin.ui.AbstractField;

public class LocalDateBox extends AbstractField<LocalDate> {

    public LocalDateBox() {
        registerRpc(new LocalDateBoxRpc() {
            @Override
            public void setValue(Long l) {
                if (l == null) {
                    LocalDateBox.this.setValue(null);
                } else {
                    LocalDate fromDateFields = LocalDate.fromDateFields(new Date(l));
                    LocalDateBox.this.setValue(fromDateFields);
                }
            }
        });
    }

    @Override
    public LocalDate getValue() {
        return (LocalDate) super.getValue();
    }

    @Override
    public Class<LocalDate> getType() {
        return LocalDate.class;
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        LocalDate value = getValue();
        getState().value = value == null? null : value.toDate().getTime();
    }
    
    @Override
    protected LocalDateBoxState getState() {
        return (LocalDateBoxState) super.getState();
    }
    
}
