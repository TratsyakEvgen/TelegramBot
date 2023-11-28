package com.tratsiak.telegram.bot.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String eventTarget;
    private String eventArgument;
    private String tariff;


}
