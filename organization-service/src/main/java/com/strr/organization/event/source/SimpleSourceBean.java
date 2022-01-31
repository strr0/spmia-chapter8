package com.strr.organization.event.source;

import com.strr.organization.event.CustomSource;
import com.strr.organization.event.model.OrganizationChangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(CustomSource.class)
public class SimpleSourceBean {
    private final CustomSource customSource;

    @Autowired
    public SimpleSourceBean(CustomSource customSource){
        this.customSource = customSource;
    }

    public void publishOrgChange(String action, String orgId){
        System.out.println(String.format("Sending Kafka message %s for Organization Id: %s", action, orgId));
        OrganizationChangeModel change =  new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(),
                action,
                orgId,
                "CorrelationId");

        customSource.output().send(MessageBuilder.withPayload(change).build());
    }
}
