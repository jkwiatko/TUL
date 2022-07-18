package com.j4c08.uwatch.data.repos;

import com.j4c08.uwatch.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User, Integer> {
}
