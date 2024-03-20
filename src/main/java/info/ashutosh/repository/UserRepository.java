package info.ashutosh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import info.ashutosh.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
	
	Optional<MyUser> findByEmail(String email);

}
