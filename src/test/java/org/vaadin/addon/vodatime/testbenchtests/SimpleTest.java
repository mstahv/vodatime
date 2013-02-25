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

        String[] expetedValues = {"2012 May 25", "foo" };

        for (int i = 0; i < expetedValues.length; i++) {
            String expected = expetedValues[i];
            String real = inputs.get(i).getAttribute("value");
			Assert.assertEquals(expected, real);
        }
        
        // Currently fails due to #10860 !?
        Assert.assertTrue(testBench.compareScreen(getReferenceImage("finalScreen.png")));

    }

}
