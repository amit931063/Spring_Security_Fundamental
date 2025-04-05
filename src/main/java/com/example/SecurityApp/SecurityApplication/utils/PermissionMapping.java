package com.example.SecurityApp.SecurityApplication.utils;

import com.example.SecurityApp.SecurityApplication.Entities.enums.Permission;
import com.example.SecurityApp.SecurityApplication.Entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.SecurityApp.SecurityApplication.Entities.enums.Permission.*;
import static com.example.SecurityApp.SecurityApplication.Entities.enums.Role.*;

public class PermissionMapping {

    private  static final Map<Role, Set<Permission>>map= Map.of(
            USER,Set.of(USER_VIEW,POST_VIEW),
            CREATER,Set.of(USER_UPDATE,POST_CREATE,POST_UPDATE),
            ADMIN,Set.of(USER_UPDATE,POST_CREATE,POST_UPDATE,USER_CREATE,USER_DELETE,POST_DELETE )
    );
    public static Set<SimpleGrantedAuthority>getAuthorityforRole( Role role){
        return  map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toSet());
    }

}
