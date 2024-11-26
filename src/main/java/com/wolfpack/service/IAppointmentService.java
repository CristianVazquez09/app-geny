package com.wolfpack.service;

import com.wolfpack.model.Appointment;

public interface IAppointmentService extends ICRUDService<Appointment, Integer> {


    Appointment saveAppointmentWithValidation(Appointment appointment) throws Exception;

}
