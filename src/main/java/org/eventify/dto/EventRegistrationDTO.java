package org.eventify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRegistrationDTO {

    // Fields for the event
    private String eventName;
    private String eventType;

    // Fields for the venue
    private String siteName;
    private String address;
    private String city;
}
