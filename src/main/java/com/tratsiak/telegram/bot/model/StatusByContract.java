package com.tratsiak.telegram.bot.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class StatusByContract implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String subscriberInfo;
    private List<StatusByService> statusByServices;

}
