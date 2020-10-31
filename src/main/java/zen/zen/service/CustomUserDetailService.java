package zen.zen.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zen.zen.entity.User;
import zen.zen.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public User findOne(Long id) {
        return userRepository.getOne(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll(){return userRepository.findAll(); }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void flush () {
        userRepository.flush();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("사용자 찾을 수 없다"));
    }
}
