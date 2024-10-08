package com.myproject.springJpaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.springJpaproject.entity.Right;
import com.myproject.springJpaproject.repository.RightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RightService {

    @Autowired
    private RightRepository rightRepository;

    public Right createRight(Right right) {
        return rightRepository.save(right);
    }

    public Optional<Right> getRightById(Long id) {
        return rightRepository.findById(id);
    }

    public List<Right> getAllRights() {
        return rightRepository.findAll();
    }

    public Right updateRight(Long id, Right updatedRight) {
        return rightRepository.findById(id)
                .map(right -> {
                    right.setRightName(updatedRight.getRightName());
                    return rightRepository.save(right);
                })
                .orElseThrow(() -> new RuntimeException("Right not found with id " + id));
    }

    public void deleteRight(Long id) {
        rightRepository.deleteById(id);
    }
}
