package com.codespace.easybasket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codespace.easybasket.dto.RegisterUserRequest;
import com.codespace.easybasket.dto.UserDto;
import com.codespace.easybasket.model.User;
import com.codespace.easybasket.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo){
        this.repo = repo;
    }

    public List<UserDto> findAll() {
        return repo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public UserDto findById(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToDto(user);
    }

    public UserDto save(RegisterUserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        User saved = repo.save(user);

        return mapToDto(saved);
    }

    public UserDto update(Long id, RegisterUserRequest request) {
        User existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setName(request.getName());
        existing.setEmail(request.getEmail());
        existing.setPassword(request.getPassword());

        User updated = repo.save(existing);

        return mapToDto(updated);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repo.deleteById(id);
    }

    private UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}