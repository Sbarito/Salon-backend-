package com.mgtu.beauty.controller.MasterController;

import com.mgtu.beauty.controller.MasterController.response.GetMasterResponse;
import com.mgtu.beauty.service.MasterService;
import com.mgtu.beauty.controller.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.mgtu.beauty.controller.MasterController.request.CreateMasterRequest;
import com.mgtu.beauty.controller.MasterController.request.AssignServiceRequest;

import java.util.List;

@RestController
@RequestMapping("beauty/master")
@AllArgsConstructor
public class MasterController {
    private final MasterService service;

    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<String>> create(@RequestBody CreateMasterRequest dto){
        service.createMaster(dto);
        return ResponseEntity.ok(Response.<String>builder().message("Мастер успешно создан").build());
    }

    @PostMapping("assign-service")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Назначить мастеру услугу")
    public ResponseEntity<Response<String>> assignService(@RequestBody AssignServiceRequest dto){
        service.assignService(dto);
        return ResponseEntity.ok(Response.<String>builder().message("Услуга назначена на мастеров").build());
    }

    @GetMapping("get/{serviceId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<List<GetMasterResponse>>> get(@PathVariable("serviceId") Integer serviceId){
        return ResponseEntity.ok(Response.<List<GetMasterResponse>>builder().data(service.getMastersByService(serviceId)).build());
    }

    @GetMapping("get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<List<GetMasterResponse>>> getAll(){
        return ResponseEntity.ok(Response.<List<GetMasterResponse>>builder().data(service.getAll()).build());
    }
}
