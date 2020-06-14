package CarRentalService.CRS.Repositories;

import CarRentalService.CRS.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long> {
}
