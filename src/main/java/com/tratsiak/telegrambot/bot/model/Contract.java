package com.tratsiak.telegrambot.bot.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tratsiak.telegrambot.bot.model.serializer.ContractSerializer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(using = ContractSerializer.class)
public class Contract {
    @JsonValue
    private long userId;
    @Pattern(regexp = "\\d{5,}", message = "Неверный формат договора")
    private String numberOfContract;
    private String action;
    private String comment;

}
