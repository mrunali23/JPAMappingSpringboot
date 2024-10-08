package com.myproject.springJpaproject.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.springJpaproject.entity.Module;
import com.myproject.springJpaproject.repository.ModuleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    public Optional<Module> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Module updateModule(Long id, Module updatedModule) {
        return moduleRepository.findById(id)
                .map(module -> {
                    module.setModuleName(updatedModule.getModuleName());
                    return moduleRepository.save(module);
                })
                .orElseThrow(() -> new RuntimeException("Module not found with id " + id));
    }

    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }
}
