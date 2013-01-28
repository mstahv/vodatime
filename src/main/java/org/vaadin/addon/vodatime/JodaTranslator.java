package org.vaadin.addon.vodatime;

import java.util.Date;

import org.joda.time.DateTime;
import org.vaadin.addon.propertytranslator.PropertyTranslator;

public class JodaTranslator extends PropertyTranslator {

    @Override
    public Object translateFromDatasource(Object value) {
        DateTime dt = (DateTime) value;
        return new Date(dt.getMillis());
    }

    @Override
    public Object translateToDatasource(Object formattedValue) throws Exception {
        Date d = (Date) formattedValue;
        return new DateTime(d.getTime());
    }

    @Override
    public Class<Date> getType() {
        return Date.class;
    }

}
