package com.example.justlee.configuration;

import com.example.justlee.entity.PrivilegeEntity;
import com.example.justlee.entity.RoleEntity;
import com.example.justlee.entity.UserEntity;
import com.example.justlee.repositoryImp.PrivilegeRepository;
import com.example.justlee.repositoryImp.RoleRepository;
import com.example.justlee.repositoryImp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        PrivilegeEntity readPrivilegeEntity
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        PrivilegeEntity writePrivilegeEntity
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<PrivilegeEntity> adminPrivilegeEntities = Arrays.asList(
                readPrivilegeEntity, writePrivilegeEntity);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivilegeEntities);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilegeEntity));

        RoleEntity adminRoleEntity = roleRepository.findByName("ROLE_ADMIN");
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("Test");
        userEntity.setLastName("Test");
        userEntity.setPassword(passwordEncoder.encode("test"));
        userEntity.setEmail("test@test.com");
        userEntity.setRoleEntities(Arrays.asList(adminRoleEntity));
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        alreadySetup = true;
    }

    @Transactional
    PrivilegeEntity createPrivilegeIfNotFound(String name) {

        PrivilegeEntity privilegeEntity = privilegeRepository.findByName(name);
        if (privilegeEntity == null) {
            privilegeEntity = new PrivilegeEntity(name);
            privilegeRepository.save(privilegeEntity);
        }
        return privilegeEntity;
    }

    @Transactional
    RoleEntity createRoleIfNotFound(
            String name, Collection<PrivilegeEntity> privilegeEntities) {

        RoleEntity roleEntity = roleRepository.findByName(name);
        if (roleEntity == null) {
            roleEntity = new RoleEntity(name);
            roleEntity.setPrivilegeEntities(privilegeEntities);
            roleRepository.save(roleEntity);
        }
        return roleEntity;
    }
}
