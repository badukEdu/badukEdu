package org.choongang.admin.edu.service;

import lombok.RequiredArgsConstructor;
import org.choongang.admin.edu.repositories.EduDataRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EduContentDeleteService {

    private final EduDataRepository eduDataRepository;

    public void delete(Long num) {
        eduDataRepository.deleteById(num);
    }
}
