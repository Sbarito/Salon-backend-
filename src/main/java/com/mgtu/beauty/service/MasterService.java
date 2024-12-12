package com.mgtu.beauty.service;

import com.mgtu.beauty.controller.MasterController.request.AssignServiceRequest;
import com.mgtu.beauty.controller.MasterController.request.CreateMasterRequest;
import com.mgtu.beauty.controller.MasterController.response.GetMasterResponse;
import com.mgtu.beauty.entity.Master;
import com.mgtu.beauty.entity.MasterAndService;
import com.mgtu.beauty.repository.MasterAndServiceRepository;
import com.mgtu.beauty.repository.MasterRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MasterService {
    private final MasterRepository masterRepository;
    private final MasterAndServiceRepository masterAndServiceRepository;
    private final ModelMapper mapper;

    public void createMaster(CreateMasterRequest dto) {
        masterRepository.save(Master.builder().fio(dto.getMasterFio()).build());
    }

    public void assignService(AssignServiceRequest dto) {
        for (Integer id: dto.getMastersId()){
            masterAndServiceRepository.save(MasterAndService.builder().masterId(id).serviceId(dto.getServiceId()).build());
        }

    }

    public List<GetMasterResponse> getMastersByService(Integer serviceId) {
        return masterAndServiceRepository.findByServiceId(serviceId).stream().map(m -> mapper.map(m, GetMasterResponse.class)).toList();
    }

    public List<GetMasterResponse> getAll() {
        return masterAndServiceRepository.findAll().stream().map(m -> mapper.map(m, GetMasterResponse.class)).toList();
    }
}
