package com.p2h.notes.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class UserPrincipal extends User {

    @EqualsAndHashCode.Include
    private String userUid;

    UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static UserPrincipal instance(String clientId, List<SimpleGrantedAuthority> authorities) {
        UserPrincipal principal = new UserPrincipal(clientId, "password", authorities);
        principal.userUid = clientId;
        return principal;
    }

    public String getUserUid() {
        return this.userUid;
    }
}
