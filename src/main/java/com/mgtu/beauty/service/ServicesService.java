package com.mgtu.beauty.service;

import com.mgtu.beauty.controller.ServicesController.ServicesController;
import com.mgtu.beauty.controller.ServicesController.request.CreateServiceRequest;
import com.mgtu.beauty.controller.ServicesController.request.UpdateServiceRequest;
import com.mgtu.beauty.controller.ServicesController.response.GetServicesResponse;
import com.mgtu.beauty.entity.ServiceEntity;
import com.mgtu.beauty.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesService {

    private ServicesRepository repository;
    private ModelMapper mapper;

    public void createService(CreateServiceRequest dto) {
        if (dto.getPrice() <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        if (dto.getServiceName().isEmpty() || dto.getServiceName().isBlank()) {
            throw new IllegalArgumentException("Наименование услуги не должно быть пустым");
        }
        repository.save(ServiceEntity.builder()
                .name(dto.getServiceName())
                .price(dto.getPrice())
                .build());
    }

    public void updateService(UpdateServiceRequest dto) {
        if (dto.getPrice() <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        if (dto.getServiceName().isEmpty() || dto.getServiceName().isBlank()) {
            throw new IllegalArgumentException("Наименование услуги не должно быть пустым");
        }
        ServiceEntity service = repository.findById(dto.getServiceId());
        if (service == null) {
            throw new IllegalArgumentException("Такой услуги не сущетсвует");
        }
        service.setName(dto.getServiceName());
        service.setPrice(dto.getPrice());
        repository.update(service);
    }

    public void deleteService(Integer serviceId) {
        repository.delete(serviceId);
    }

    public List<GetServicesResponse> getAll() {
        return repository.findAll().stream().map(s -> mapper.map(s, GetServicesResponse.class)).toList();
    }
}
