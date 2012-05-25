package org.vaadin.addon.vodatime;

import java.util.Map;

import org.joda.time.LocalDate;
import org.vaadin.addon.vodatime.client.ui.VLocalDateBox;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ClientWidget;

@ClientWidget(VLocalDateBox.class)
public class LocalDateBox extends AbstractField {

    @Override
    public Class<?> getType() {
        return LocalDate.class;
    }

    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        if (getValue() != null) {
            // client-server communication as epoc time
            target.addVariable(this, "v", getValue().toDate().getTime());
        }
    }

    @Override
    public void changeVariables(Object source, Map<String, Object> variables) {
        super.changeVariables(source, variables);
        Long l = (Long) variables.get("v");
        setValue(new LocalDate(l), true);
    }

    @Override
    public LocalDate getValue() {
        return (LocalDate) super.getValue();
    }

}
