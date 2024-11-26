package com.wolfpack.service.impl;

import com.wolfpack.model.HourAppointment;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IHourAppointmentRepo;
import com.wolfpack.service.IHourAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HourAppointmentImpl extends CRUDServiceImpl<HourAppointment,Integer> implements IHourAppointmentService {


    private final IHourAppointmentRepo repo;

    @Override
    protected IGenericRepo<HourAppointment, Integer> getRepo() {
        return repo;
    }

}
