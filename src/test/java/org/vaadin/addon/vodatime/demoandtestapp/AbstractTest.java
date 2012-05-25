package org.vaadin.addon.vodatime.demoandtestapp;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class AbstractTest extends Window {

    private VerticalLayout content;

    public AbstractTest() {
        content = new VerticalLayout();
        setContent(content);
    }

    @Override
    public void attach() {
        super.attach();
        setup();
        showNotification(getClass().getSimpleName(), getDescription(),
                Notification.TYPE_WARNING_MESSAGE);
    }

    protected void setup() {
        Component map = getTestComponent();
        content.setSizeFull();
        content.addComponent(map);
        content.setExpandRatio(map, 1);
    }

    abstract Component getTestComponent();

}
