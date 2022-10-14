package com.app.hotel.repository;

import com.app.hotel.model.entity.Guest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

  Optional<Guest> findGuestByEmail(String email);

  Boolean existsGuestByEmail(String email);
}
