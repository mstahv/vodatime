package org.vaadin.addon.vodatime.demoandtestapp;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.vaadin.addon.vodatime.LocalDateBox;
import org.vaadin.addon.vodatime.VodaDateField;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class BasicTest extends AbstractTest {

    private FormFieldFactory fieldFactory = new FormFieldFactory() {

        public Field createField(Item item, Object propertyId,
                Component uiContext) {
            Field f = null;
            Class<?> type = item.getItemProperty(propertyId).getType();
            if (type == DateTime.class) {
                f = new VodaDateField();
            } else if (type == LocalDate.class) {
                f = new LocalDateBox();
            }
            if (f != null) {
                f.setCaption(DefaultFieldFactory
                        .createCaptionByPropertyId(propertyId));
                return f;
            }
            return DefaultFieldFactory.get().createField(item, propertyId,
                    uiContext);
        }
    };

    @Override
    public String getDescription() {
        return "A simple test";
    }

    @Override
    Component getTestComponent() {

        final Label msg = new Label();

        final Form form = new Form();
        form.setCaption("Test form with bean with Joda fields");
        form.setFormFieldFactory(fieldFactory);

        BeanItem<SampleBean> beanItem = new BeanItem<SampleBean>(
                new SampleBean());

        form.setItemDataSource(beanItem);

        Button button = new Button("Update pojo state");
        button.addListener(new Button.ClickListener() {
            @SuppressWarnings("unchecked")
            public void buttonClick(ClickEvent event) {
                showNotification(((BeanItem<SampleBean>) form
                        .getItemDataSource()).getBean().toString());
            }
        });
        form.getFooter().addComponent(button);
        button = new Button("Prefill values");
        button.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {

                SampleBean sampleBean = new SampleBean();
                sampleBean.setText("foo");
                sampleBean.setDateTime(new DateTime(2012, 5, 25, 10, 12));
                sampleBean.setLocalDate(new LocalDate(2012, 5, 25));
                form.setItemDataSource(new BeanItem<SampleBean>(sampleBean));

            }
        });
        form.getFooter().addComponent(button);

        button = new Button("Open component property editor");
        button.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                ComponentEditor componentEditor = new ComponentEditor(
                        getContent());
                addWindow(componentEditor);
            }
        });
        form.getFooter().addComponent(button);

        return form;
    }

}
