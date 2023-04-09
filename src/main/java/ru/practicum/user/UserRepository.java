package ru.practicum.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

interface UserRepository extends JpaRepository<User, Long> {

}