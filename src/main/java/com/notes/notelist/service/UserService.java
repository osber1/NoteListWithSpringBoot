package com.notes.notelist.service;

import com.notes.notelist.exception.NotFoundException;
import com.notes.notelist.model.User;
import com.notes.notelist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public Collection<User> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User getOne(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no id: " + id));
    }

    public User update(User user) {
        if (!repository.existsById(user.getId())) {
            throw new NotFoundException("There is no id: " + user.getId());
        }
        return repository.save(user);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("There is no id: " + id);
        }
        repository.deleteById(id);
    }
}