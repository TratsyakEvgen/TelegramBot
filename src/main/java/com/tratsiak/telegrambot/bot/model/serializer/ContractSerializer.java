package com.tratsiak.telegrambot.bot.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tratsiak.telegrambot.bot.model.Contract;

import java.io.IOException;

public class ContractSerializer extends JsonSerializer<Contract> {
    @Override
    public void serialize(Contract contract, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("contract", contract.getNumberOfContract());
        gen.writeStringField("comment", contract.getComment());

        gen.writeFieldName("employee");
        gen.writeStartObject();
        gen.writeStringField("telegramId", String.valueOf(contract.getUserId()));
        gen.writeEndObject();

        gen.writeFieldName("operation");
        gen.writeStartObject();
        gen.writeStringField("name", contract.getAction());
        gen.writeEndObject();

        gen.writeEndObject();




    }
}
