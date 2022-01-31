package com.strr.licenses.event.handler;

import com.strr.licenses.event.CustomSink;
import com.strr.licenses.event.model.OrganizationChangeModel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(CustomSink.class)
public class OrganizationChangeHandler {
    @StreamListener("inboundOrgChanges")
    public void loggerSink(OrganizationChangeModel orgChange) {
        System.out.println(String.format("Received a message of type %s", orgChange.getType()));
        switch(orgChange.getAction()){
            case "GET":
                System.out.println(String.format("Received a GET event from the organization service for organization id %s", orgChange.getOrganizationId()));
                break;
            case "SAVE":
                System.out.println(String.format("Received a SAVE event from the organization service for organization id %s", orgChange.getOrganizationId()));
                break;
            case "UPDATE":
                System.out.println(String.format("Received a UPDATE event from the organization service for organization id %s", orgChange.getOrganizationId()));
                //organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
                break;
            case "DELETE":
                System.out.println(String.format("Received a DELETE event from the organization service for organization id %s", orgChange.getOrganizationId()));
                //organizationRedisRepository.deleteOrganization(orgChange.getOrganizationId());
                break;
            default:
                System.out.println(String.format("Received an UNKNOWN event from the organization service of type %s", orgChange.getType()));
                break;

        }
    }
}
