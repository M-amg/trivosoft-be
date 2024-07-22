package com.zenthrex.caravan.mappers;

import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(componentModel = "spring")
public class DateMapper {
    public Date convertOffsetDateTimeToDate(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Date.from(offsetDateTime.toInstant());
    }

    public OffsetDateTime convertDateToOffsetDateTime(Date date) {
        return date == null ? null : date.toInstant().atOffset(OffsetDateTime.now().getOffset());
    }

    public LocalDate asLocalDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

    public OffsetDateTime asOffsetDateTime(LocalDateTime localDateTime) {
        return OffsetDateTime.of(localDateTime, ZoneOffset.UTC);
    }
}
