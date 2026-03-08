package org.mrpo.lab1.services;

import lombok.RequiredArgsConstructor;
import org.mrpo.lab1.models.Address;
import org.mrpo.lab1.repositories.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AddressService {
    private final AddressRepository addressRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(long id) {
        return addressRepository.findById(id).orElse(null);
    }
}
