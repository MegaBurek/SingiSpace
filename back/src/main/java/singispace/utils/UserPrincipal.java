package singispace.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import singispace.domain.AccountData;
import singispace.domain.AuthProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {

    private String id;
    private String username;
    private String password;
    private boolean emailVerified;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;


    public UserPrincipal(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(AccountData accountData) {
        if (accountData.getProvider() == AuthProvider.local) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(accountData.getPermission().getAuthority()));
            return new UserPrincipal(accountData.getId(), accountData.getUsername(), accountData.getPassword(), authorities);
        }

        return null;

    }

    public static UserPrincipal create(AccountData accountData, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(accountData);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }


    public String getId() {
        return id;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getName() {
        return String.valueOf(id);
    }
}
