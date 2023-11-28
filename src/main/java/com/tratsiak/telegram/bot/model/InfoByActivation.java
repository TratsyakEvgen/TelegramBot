package com.tratsiak.telegram.bot.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class InfoByActivation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String subscriberInfo;
    private Map<String, Event> eventMap = new HashMap<>();
}
