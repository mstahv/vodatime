/*
 * Copyright 2012 Vaadin Community.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.addon.vodatime.client.vaadin;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.shared.AbstractFieldState;
import com.vaadin.shared.ui.Connect;
import java.util.Date;
import org.vaadin.addon.vodatime.LocalDateBox;
import org.vaadin.addon.vodatime.client.ui.VLocalDateBox;

/**
 *
 * @author mattitahvonenitmill
 */
@Connect(LocalDateBox.class)
public class LocalDateBoxConnector extends AbstractFieldConnector {

    LocalDateBoxRpc rpc = RpcProxy.create(LocalDateBoxRpc.class,this);
    
    @Override
    public VLocalDateBox getWidget() {
        return (VLocalDateBox) super.getWidget();
    }

    @Override
    public LocalDateBoxState getState() {
        return (LocalDateBoxState) super.getState();
    }

    @Override
    protected void init() {
        super.init();
        
        getWidget().addValueChangeHandler(new ValueChangeHandler<Date>(){

            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date value = event.getValue();
                Long l = value == null ? null : value.getTime();
                rpc.setValue(l);
            }
        });
    }
    
}
