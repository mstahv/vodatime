package org.vaadin.addon.vodatime;

import org.joda.time.DateTime;

import com.vaadin.ui.DateField;

public class VodaDateField extends DateField {

    private JodaTranslator translator = new JodaTranslator();

    public VodaDateField() {
        setPropertyDataSource(translator);
        // set saner resolution then milliseconds
        setResolution(RESOLUTION_MIN);
    }

    /**
     * A setter method for date value to help usage when not using via Form data
     * binding.
     * 
     * @param dt
     */
    public void setDateTime(DateTime dt) {
        setValue(translator.translateFromDatasource(dt));
    }

    /**
     * @return the date value as Joda DateTime
     */
    public DateTime getDateTime() {
        try {
            return (DateTime) translator.translateToDatasource(getValue());
        } catch (Exception e) {
            return null;
        }
    }

}
