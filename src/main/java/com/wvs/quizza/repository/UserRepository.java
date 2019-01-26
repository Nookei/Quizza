package com.wvs.quizza.repository;

import com.wvs.quizza.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
