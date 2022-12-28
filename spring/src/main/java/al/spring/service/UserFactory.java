package al.spring.service;

import al.spring.dto.UserRequestDto;
import al.spring.model.User;
import al.spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFactory {
    private final UserRepository userRepository;

    @Transactional
    public User registerAccount(UserRequestDto userRequestDto){
        var user = createUser(userRequestDto);

        return user;
    }

    @Transactional
    public User createUser(UserRequestDto userRequestDto){
        var user = new User();
        user.setName(userRequestDto.getName());
        userRepository.save(user);

        return user;
    }
}
