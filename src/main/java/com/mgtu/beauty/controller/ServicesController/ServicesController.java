package com.mgtu.beauty.controller.ServicesController;

import com.mgtu.beauty.controller.ServicesController.request.CreateServiceRequest;
import com.mgtu.beauty.controller.ServicesController.request.UpdateServiceRequest;
import com.mgtu.beauty.controller.ServicesController.response.GetServicesResponse;
import com.mgtu.beauty.service.ServicesService;
import com.mgtu.beauty.controller.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RestController
@RequestMapping("beauty/services")
public class ServicesController {
    private final ServicesService service;

    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<String>> createService(@RequestBody CreateServiceRequest dto) {
        service.createService(dto);
        return ResponseEntity.ok(Response.<String>builder().message("Новая услуга добавлена").build());
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<String>> updateService(@RequestBody UpdateServiceRequest dto) {
        service.updateService(dto);
        return ResponseEntity.ok(Response.<String>builder().message("Услуга обновлена").build());
    }

    @DeleteMapping("delete/{serviceId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<String>> deleteService(@PathVariable("serviceId") Integer serviceId) {
        service.deleteService(serviceId);
        return ResponseEntity.ok(Response.<String>builder().message("Услуга удалена").build());
    }

    @GetMapping("get-all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<List<GetServicesResponse>>> getAllService() {
        return ResponseEntity.ok(Response.<List<GetServicesResponse>>builder().data(service.getAll()).build());
    }
}
