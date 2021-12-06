package ru.iteco.account.service;

import org.springframework.stereotype.Component;
import ru.iteco.account.model.AddressDto;
import ru.iteco.account.model.UserDto;
import ru.iteco.account.model.UserNotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
class UserServiceImpl implements UserService {

    private final Map<Integer, UserDto> userDtoMap = new ConcurrentHashMap<>();
    private final AtomicInteger sequenceId = new AtomicInteger(1);

    @PostConstruct
    void init() {
        int id = sequenceId.getAndIncrement();
        userDtoMap.put(
                id,
                new UserDto(id, "Fill", "fill@mail.com",
                        new AddressDto("RUSSIA", "MOSCOW", "LENINA", "1")));
    }

    @Override
    public List<UserDto> findAll() {
        return new ArrayList<>(userDtoMap.values());
    }

    @Override
    public UserDto findById(Integer id) {
        UserDto userDto = userDtoMap.get(id);
        if (userDto == null) {
            throw new UserNotFoundException("Пользователь не найден!", id);
        }
        return userDto;
    }

    @Override
    public UserDto create(UserDto userDto) {
        int id = sequenceId.getAndIncrement();
        userDto.setId(id);
        userDtoMap.put(id, userDto);
        return userDto;
    }

    @Override
    public UserDto update(UserDto userDto) {
        UserDto userDtoFromMap = userDtoMap.get(userDto.getId());
        if (userDtoFromMap == null) {
            return null;
        } else {
            userDtoMap.put(userDto.getId(), userDto);
            return userDto;
        }
    }

    @Override
    public void delete(Integer id) {
        userDtoMap.remove(id);
    }

}
