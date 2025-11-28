package com.siemens.policyholderapi.models;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;
import java.util.UUID;

public class CustomPolicyIdGenerator implements BeforeExecutionGenerator {
    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }

    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o, Object o1, EventType eventType) {
        return "POLICY_"+ UUID.randomUUID().toString().substring(0,8);
    }
}
