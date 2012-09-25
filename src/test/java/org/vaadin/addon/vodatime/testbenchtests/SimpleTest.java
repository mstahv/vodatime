package org.vaadin.addon.vodatime.testbenchtests;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SimpleTest extends AbstractTestBenchTest {

    @Test
    public void basic() throws IOException, AssertionError {
        startBrowser();

        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

        driver.get(BASEURL + "BasicTest");

        WebElement prefillbutton = driver.findElement(By
                .xpath("//*[text() = 'Prefill values']"));
        prefillbutton.click();

        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        String[] expetedValues = { "5/25/12 10:12 AM", "2012 May 25", "foo" };

        for (int i = 0; i < expetedValues.length; i++) {
            String expected = expetedValues[i];
            Assert.assertEquals(expected, inputs.get(i).getAttribute("value"));
        }
        
        testBench.resizeViewPortTo(640, 400);
        Assert.assertTrue(testBench.compareScreen(getReferenceImage("finalScreen.png")));

    }

}
