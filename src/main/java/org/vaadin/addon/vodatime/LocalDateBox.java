package org.vaadin.addon.vodatime;

import com.vaadin.shared.AbstractFieldState;
import com.vaadin.ui.AbstractField;
import java.util.Date;
import org.joda.time.LocalDate;
import org.vaadin.addon.vodatime.client.vaadin.LocalDateBoxRpc;
import org.vaadin.addon.vodatime.client.vaadin.LocalDateBoxState;

public class LocalDateBox extends AbstractField {

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
    public Class getType() {
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
