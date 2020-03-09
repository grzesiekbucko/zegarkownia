package pl.marko.zegarki.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marko.zegarki.entity.Role;
import pl.marko.zegarki.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

}
