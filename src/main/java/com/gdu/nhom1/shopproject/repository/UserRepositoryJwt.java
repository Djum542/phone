//package com.gdu.nhom1.shopproject.repository;
//
//import java.util.Optional;
//
//import com.gdu.nhom1.shopproject.models.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepositoryJwt extends JpaRepository<User, Integer> {
//	//@Query(value = "SELECT * FROM user u, users_roles ur, role r WHERE u.id = ur.user_id AND u.email LIKE %_email%_ AND ur.role_id = r.id AND r.name = 'ROLE_USER'", nativeQuery = true)
//	Optional<User> findByEmail(String email);
////	@Query(value = "SELECT * FROM user u, users_roles ur, role r WHERE u.id = ur.user_id AND u.email LIKE %_email%_ AND ur.role_id = r.id AND r.name = 'ROLE_USER'", nativeQuery = true)
////	Optional<User> findAllBy();
//}
