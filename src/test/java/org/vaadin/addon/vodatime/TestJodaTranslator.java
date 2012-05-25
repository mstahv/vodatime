package org.vaadin.addon.vodatime;

import static org.junit.Assert.assertEquals;

import java.util.Date;

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
                .translateFromDatasource(nowJodaDateTime);

        assertEquals(nowJavaDate, convertedToJava);

        DateTime convertedToJoda = (DateTime) jodaTranslator
                .translateToDatasource(nowJavaDate);

        assertEquals(nowJodaDateTime, convertedToJoda);

    }

}
