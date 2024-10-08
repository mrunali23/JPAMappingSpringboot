package com.myproject.springJpaproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproject.springJpaproject.entity.Right;
import com.myproject.springJpaproject.service.RightService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rights")
public class RightController {

    @Autowired
    private RightService rightService;

    @PostMapping
    public ResponseEntity<Right> createRight(@RequestBody Right right) {
        Right createdRight = rightService.createRight(right);
        return ResponseEntity.ok(createdRight);
    }

    @GetMapping
    public ResponseEntity<List<Right>> getAllRights() {
        List<Right> rights = rightService.getAllRights();
        return ResponseEntity.ok(rights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Right> getRightById(@PathVariable Long id) {
        Optional<Right> right = rightService.getRightById(id);
        return right.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Right> updateRight(@PathVariable Long id, @RequestBody Right updatedRight) {
        try {
            Right right = rightService.updateRight(id, updatedRight);
            return ResponseEntity.ok(right);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRight(@PathVariable Long id) {
        rightService.deleteRight(id);
        return ResponseEntity.noContent().build();
    }
}
