package com.strr.licenses.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomSink {
    String INPUT = "inboundOrgChanges";

    @Input(INPUT)
    SubscribableChannel input();
}
