package com.api.peruprime.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.peruprime.modelo.User;

@Repository
public interface UserRepositorio extends JpaRepository<User, Long> {
//	INSERT INTO `peruprime`.`user` (`id`, `email`) VALUES ('1', 'admin@gmail.com');
	@Query(value = "INSERT INTO 'peruprime'.'user' ('id', 'email')  VALUES (LIKE %:id% , LIKE %:email%)", nativeQuery = true)
	public void insert(@Param(value = "email") String email, @Param(value = "id") Long id);
	
	public User findByEmail(String email);

}