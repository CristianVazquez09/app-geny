package com.wolfpack.repo;

import com.wolfpack.model.Appointment;
import com.wolfpack.model.HourAppointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IAppointmentRepo extends IGenericRepo<Appointment, Integer>{
    @Query("SELECT COUNT(a) = 0 FROM Appointment a " +
            "WHERE a.dateAppointment = :date AND a.hourAppointment = :hourAppointment")
    boolean isHourAvailable(@Param("date") LocalDate date, @Param("hourAppointment") HourAppointment hourAppointment);


}
