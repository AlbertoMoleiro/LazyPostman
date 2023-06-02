package com.lazypostman.routemanagement.dto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lazypostman.routemanagement.model.Itinerary;
import jakarta.persistence.*;
import java.io.IOException;
import java.util.List;

@Converter
public class ItineraryConverter implements AttributeConverter<List<Itinerary>, String>{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Itinerary> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Itinerary> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, objectMapper.getTypeFactory().constructCollectionType(List.class, Itinerary.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
