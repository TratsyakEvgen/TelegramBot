package com.tratsiak.telegrambot.bot.model;


import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tratsiak.telegrambot.bot.model.serializer.ContractSerializer;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(using = ContractSerializer.class)
public class Contract implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonValue
    private long userId;
    @Pattern(regexp = "\\d{5,}", message = "Неверный формат договора")
    private String numberOfContract;
    private String action;
    private String comment;
}
