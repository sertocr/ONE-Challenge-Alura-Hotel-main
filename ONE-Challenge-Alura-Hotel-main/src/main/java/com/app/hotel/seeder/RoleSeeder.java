package com.app.hotel.seeder;

import com.app.hotel.config.security.RoleType;
import com.app.hotel.model.entity.Role;
import com.app.hotel.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Autowired
  public RoleSeeder(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  @Order(1)
  public void run(String... args) throws Exception {
    seedRoleTable();
  }

  private void seedRoleTable() {
    if (roleRepository.count() == 0) {
      createRole(RoleType.ADMIN);
      createRole(RoleType.USER);
    }
  }

  private void createRole(RoleType roleType) {
    Role role = new Role();
    role.setName(roleType.getFullRoleName());
    roleRepository.save(role);
  }
}
