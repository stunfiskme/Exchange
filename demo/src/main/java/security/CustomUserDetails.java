package security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.UserAccounts;

public class CustomUserDetails implements UserDetails {

    private final UserAccounts user;

    public CustomUserDetails(UserAccounts user) {
        this.user = user;
    }

    public UserAccounts getUser(){
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getRoles().stream()
               .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
               .collect(Collectors.toList());
}


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    //Email is username for this project!
    @Override
    public String getUsername() {
        return user.getEmail();
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
}
