package org.vaadin.addon.vodatime;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.junit.Test;

public class TestJodaTranslator {

    @Test
    public void test() throws Exception {

        long currentTimeMillis = System.currentTimeMillis();

        Date nowJavaDate = new Date(currentTimeMillis);
        DateTime nowJodaDateTime = new DateTime(currentTimeMillis);

        JodaTranslator jodaTranslator = new JodaTranslator();

        Date convertedToJava = (Date) jodaTranslator
                .convertToPresentation(nowJodaDateTime, Locale.getDefault());

        assertEquals(nowJavaDate, convertedToJava);

        DateTime convertedToJoda = (DateTime) jodaTranslator
                .convertToModel(nowJavaDate,Locale.getDefault());

        assertEquals(nowJodaDateTime, convertedToJoda);

    }

}
