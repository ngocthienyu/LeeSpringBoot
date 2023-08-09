package com.example.justlee.service;

import com.example.justlee.entity.PrivilegeEntity;
import com.example.justlee.entity.RoleEntity;
import com.example.justlee.entity.UserEntity;
import com.example.justlee.repositoryImp.RoleRepository;
import com.example.justlee.repositoryImp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);
        if (Objects.isNull(userEntity)) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(
                            roleRepository.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(), userEntity.getPassword(), userEntity.isEnabled(), true, true,
                true, getAuthorities(userEntity.getRoleEntities()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<RoleEntity> roleEntities) {
        return getGrantedAuthorities(getPrivileges(roleEntities));
    }

    private List<String> getPrivileges(Collection<RoleEntity> roleEntities) {

        List<String> privileges = new ArrayList<>();
        List<PrivilegeEntity> collection = new ArrayList<>();
        for (RoleEntity roleEntity : roleEntities) {
            privileges.add(roleEntity.getName());
            collection.addAll(roleEntity.getPrivilegeEntities());
        }
        for (PrivilegeEntity item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}