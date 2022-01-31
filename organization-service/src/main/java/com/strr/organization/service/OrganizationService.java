package com.strr.organization.service;

import com.strr.organization.event.source.SimpleSourceBean;
import com.strr.organization.model.Organization;
import com.strr.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    private final SimpleSourceBean simpleSourceBean;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, SimpleSourceBean simpleSourceBean) {
        this.organizationRepository = organizationRepository;
        this.simpleSourceBean = simpleSourceBean;
    }

    public Organization getOrg(String id) {
        return organizationRepository.findById(id).orElse(null);
    }

    public void saveOrg(Organization org){
        org.setId( UUID.randomUUID().toString());
        System.out.println(String.format("save org[%s]", org.getId()));
        simpleSourceBean.publishOrgChange("SAVE", org.getId());
        // organizationRepository.save(org);
    }

    public void updateOrg(Organization org){
        System.out.println(String.format("update org[%s]", org.getId()));
        simpleSourceBean.publishOrgChange("UPDATE", org.getId());
        // organizationRepository.save(org);
    }

    public void deleteOrg(String id){
        System.out.println(String.format("delete org[%s]", id));
        simpleSourceBean.publishOrgChange("DELETE", id);
        // organizationRepository.deleteById(id);
    }
}
