package com.wolfpack.service.impl;

import com.wolfpack.exception.InvalidAppointmentDateException;
import com.wolfpack.model.Appointment;
import com.wolfpack.model.HourAppointment;
import com.wolfpack.repo.IAppointmentRepo;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.service.IAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class AppointmentImpl extends CRUDServiceImpl<Appointment,Integer> implements IAppointmentService{


    private final IAppointmentRepo repo;


    @Override
    protected IGenericRepo<Appointment, Integer> getRepo() {
        return repo;
    }

    @Override
    public Appointment saveAppointmentWithValidation(Appointment appointment) throws Exception {

        LocalDate date = appointment.getDateAppointment();
        HourAppointment hour = appointment.getHourAppointment();


        if(!repo.isHourAvailable(date, hour)){
            throw new InvalidAppointmentDateException("Date Not available");
        }


        return repo.save(appointment);
    }
}

