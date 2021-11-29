package com.backend.market.art.repository;


import com.backend.market.art.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users , Long> {
//    List<Users> findByPublished(boolean published);
//    List<Users> findByTitleContaining(String title);
}
