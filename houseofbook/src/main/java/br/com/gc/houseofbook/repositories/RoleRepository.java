package br.com.gc.houseofbook.repositories;

import br.com.gc.houseofbook.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
