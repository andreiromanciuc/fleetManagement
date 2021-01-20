package com.andrei.fleetManagement.partnerTests;

import com.andrei.fleetManagement.domain.Partner;
import com.andrei.fleetManagement.service.PartnerService;
import com.andrei.fleetManagement.transfer.CreatePartner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class PartnerServiceIntegrationTests {

    @Autowired
    private PartnerService partnerService;

//    @Test
//    public void createPartner_whenValidRequests_thenPartnerIsCreated() {
//        CreatePartner createPartner = new CreatePartner();
//        createPartner.setName("SC Partner SRL");
//        createPartner.setAddress("str. Paris");
//        createPartner.setBankAccount("ROINGB12136546xxx54844");
//        createPartner.setCif("RO789654");
//        createPartner.setContactPerson("Test Partner");
//        createPartner.setEmail("test@partner.com");
//        createPartner.setPassword("123xxx123");
//        createPartner.setPhoneNumber("000123321");
//
//        Partner partner = partnerService.createPartner(createPartner);
//
//        assertThat(partner, notNullValue());
//        assertThat(partner.getId(), greaterThan(0L));
//        assertThat(partner.getAddress(), is(createPartner.getAddress()));
//        assertThat(partner.getBankAccount(), is(createPartner.getBankAccount()));
//        assertThat(partner.getCif(), is(createPartner.getCif()));
//        assertThat(partner.getContactPerson(), is(createPartner.getContactPerson()));
//        assertThat(partner.getEmail(), is(createPartner.getEmail()));
//        assertThat(partner.getPassword(), is(createPartner.getPassword()));
//        assertThat(partner.getPhoneNumber(), is(createPartner.getPhoneNumber()));
//
//    }
}
