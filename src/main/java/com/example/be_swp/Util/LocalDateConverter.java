package com.example.be_swp.Util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, LocalDateTime> {
    @Override
    public LocalDateTime convertToDatabaseColumn(LocalDate localDate) {
        return localDate != null ? localDate.atStartOfDay() : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.toLocalDate() : null;
    }
}