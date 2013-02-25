package org.vaadin.addon.vodatime;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.vaadin.data.util.converter.Converter;

public class JodaTranslator implements Converter<Date, DateTime> {


	@Override
	public Class<DateTime> getModelType() {
		return DateTime.class;
	}

	@Override
	public Class<Date> getPresentationType() {
		return Date.class;
	}
	@Override
	public DateTime convertToModel(Date value, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
        return new DateTime(value.getTime());
	}

	@Override
	public Date convertToPresentation(DateTime value, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
        return new Date(value.getMillis());
	}

}
