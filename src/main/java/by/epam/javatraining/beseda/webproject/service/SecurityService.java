package by.epam.javatraining.beseda.webproject.service;

import by.epam.javatraining.beseda.webproject.dao.interfacedao.UserInterface;
import by.epam.javatraining.beseda.webproject.entity.user.User;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.beseda.webproject.util.LoggerName.ERROR_LOGGER;

@Service
@Transactional
public class SecurityService implements UserDetailsService {

    private Logger log = Logger.getLogger(ERROR_LOGGER);

    @Autowired
    private UserInterface userInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userInterface.getUserByLogin(username);
        List<GrantedAuthority> authorities
                = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {
        List<GrantedAuthority> result = new ArrayList<>();
        result.add(new SimpleGrantedAuthority(userRole));
        return result;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), Hex.encodeHexString(user.getPassword()),
                true, true, true, true, authorities);
    }

}
