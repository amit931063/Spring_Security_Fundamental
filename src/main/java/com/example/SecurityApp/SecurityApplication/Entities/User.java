package com.example.SecurityApp.SecurityApplication.Entities;

import com.example.SecurityApp.SecurityApplication.Entities.enums.Permission;
import com.example.SecurityApp.SecurityApplication.Entities.enums.Role;
import com.example.SecurityApp.SecurityApplication.utils.PermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User implements UserDetails {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(unique=true)
   private String email;

   private String password;
   private  String name;

   @ElementCollection(fetch =FetchType.EAGER)
@Enumerated(EnumType.STRING)
private Set<Role>roles;


//    @ElementCollection(fetch =FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//   private Set<Permission>permissions;

// this method  is used to gain the all authority to the user //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//       Set<SimpleGrantedAuthority>authorities= roles.stream().map(roles->new SimpleGrantedAuthority("ROLE_"+roles.name()))
//                .collect(Collectors.toSet());
//
//       // here we are adding all the permission to the role //
//       permissions. forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.name())));



        Set<SimpleGrantedAuthority>authorities=new HashSet<>();


        roles.forEach( role -> {
            Set<SimpleGrantedAuthority>permissions= PermissionMapping.getAuthorityforRole(role);
            authorities.addAll(permissions);
            authorities.add(new SimpleGrantedAuthority("Role_"+role.name()));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
