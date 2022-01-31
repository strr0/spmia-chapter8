package com.strr.organization.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomSource {
    String OUTPUT = "outboundOrgChanges";

    @Output(OUTPUT)
    MessageChannel output();
}
