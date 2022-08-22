package com.nisum.authenticationservice.dto.mapper;

import com.nisum.authenticationservice.dto.PhoneDto;
import com.nisum.authenticationservice.model.Phone;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

public class PhoneMapper {

    public static Collection<PhoneDto> toPhoneDtos(final Collection<Phone> phones) {
        Objects.requireNonNull(phones, "The list of phones is null");

        Collection<PhoneDto> phoneDtos = new LinkedList<>();
        for (Phone phone : phones) {
            PhoneDto phoneDto = toPhoneDto(phone);
            phoneDtos.add(phoneDto);
        }

        return phoneDtos;
    }

    public static PhoneDto toPhoneDto(final Phone phone) {
        Objects.requireNonNull(phone, "The phone is null");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber(phone.getNumber());
        phoneDto.setCityCode(phone.getCityCode());
        phoneDto.setCountryCode(phone.getCountryCode());

        return phoneDto;
    }

    public static Collection<Phone> toPhones(Collection<PhoneDto> phoneDtos) {
        Objects.requireNonNull(phoneDtos, "The list of phoneDtos is null");

        Collection<Phone> phones = new LinkedList<>();
        for (PhoneDto phoneDto : phoneDtos) {
            Phone phone = toPhone(phoneDto);
            phones.add(phone);
        }

        return phones;
    }

    private static Phone toPhone(PhoneDto phoneDto) {
        Objects.requireNonNull(phoneDto, "The phoneDto is null");

        Phone phone = new Phone();
        phone.setNumber(phoneDto.getNumber());
        phone.setCityCode(phoneDto.getCityCode());
        phone.setCountryCode(phoneDto.getCountryCode());

        return phone;
    }
}
