package com.example.back_end.reponsitories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.example.back_end.model.user;
public interface UserReponsitories extends JpaRepository<user, Long> {

  Optional<user>   findByEmail(String email);


}
