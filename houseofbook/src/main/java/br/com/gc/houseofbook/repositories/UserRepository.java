package br.com.gc.houseofbook.repositories;

import br.com.gc.houseofbook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
