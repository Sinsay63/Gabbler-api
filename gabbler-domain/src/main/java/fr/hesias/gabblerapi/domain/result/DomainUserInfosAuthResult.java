package fr.hesias.gabblerapi.domain.result;

import fr.hesias.gabblerapi.domain.model.DomainAccessStatus;
import fr.hesias.gabblerapi.domain.model.DomainUserAuth;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DomainUserInfosAuthResult extends DomainResultable implements UserDetails {


    private final DomainUserAuth userAuthInfo;

    private final List<GrantedAuthority> authorities;

    public DomainUserInfosAuthResult(DomainAccessStatus domainAccessStatus, DomainUserAuth userAuthInfo) {

        super(domainAccessStatus);

        this.userAuthInfo = userAuthInfo;
        authorities = Arrays.stream(userAuthInfo.getUser().getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {

        return userAuthInfo.getPassword();
    }

    @Override
    public String getUsername() {

        return userAuthInfo.getUser().getEmail();
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
