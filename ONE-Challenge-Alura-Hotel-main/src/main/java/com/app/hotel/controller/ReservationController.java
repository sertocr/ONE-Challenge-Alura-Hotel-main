package com.app.hotel.controller;

import com.app.hotel.exception.ReservationAlreadyDoneException;
import com.app.hotel.exception.ReservationNotFoundException;
import com.app.hotel.model.request.ReservationRequest;
import com.app.hotel.model.response.ReservationResponse;
import com.app.hotel.model.response.ListReservationResponse;
import com.app.hotel.service.CreateReservationImpl;
import com.app.hotel.service.DeleteReservationImpl;
import com.app.hotel.service.GetAllReservationImpl;
import com.app.hotel.service.UpdateReservationImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

  private final CreateReservationImpl createReservation;

  private final GetAllReservationImpl getAllReservation;

  private final UpdateReservationImpl updateReservation;

  private final DeleteReservationImpl deleteReservation;

  @Autowired
  public ReservationController(CreateReservationImpl createReservation,
      GetAllReservationImpl getAllReservation, UpdateReservationImpl updateReservation,
      DeleteReservationImpl deleteReservation) {
    this.createReservation = createReservation;
    this.getAllReservation = getAllReservation;
    this.updateReservation = updateReservation;
    this.deleteReservation = deleteReservation;
  }

  @PostMapping("/create")
  public ResponseEntity<ReservationResponse> create(@Valid @RequestBody
  ReservationRequest request) throws ReservationAlreadyDoneException {
    ReservationResponse response = createReservation.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/getAll")
  public ResponseEntity<ListReservationResponse> getAll() {
    ListReservationResponse listReservationResponse = getAllReservation.getAll();
    return ResponseEntity.status(HttpStatus.OK).body(listReservationResponse);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<ReservationResponse> update(@PathVariable Long id, @RequestBody @Valid ReservationRequest request)
      throws ReservationNotFoundException {
    ReservationResponse response = updateReservation.update(id, request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) throws ReservationNotFoundException {
    deleteReservation.delete(id);
    return ResponseEntity.ok().build();
  }
}
