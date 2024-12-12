package com.mgtu.beauty.controller.RecordController;

import com.mgtu.beauty.controller.RecordController.request.CreateRecordRequest;
import com.mgtu.beauty.controller.RecordController.response.GetRecordResponse;
import com.mgtu.beauty.controller.Response;
import com.mgtu.beauty.entity.TimeSlot;
import com.mgtu.beauty.service.RecordService;
import com.mgtu.beauty.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("beauty/record")
public class RecordController {
    private final RecordService recordService;
    private final TokenService tokenService;

    @GetMapping("get-time-slots")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<List<TimeSlot>>> getTimeSlots(@RequestParam("date") LocalDate date, @RequestParam("master_id") Integer masterId) {
        return ResponseEntity.ok(Response.<List<TimeSlot>>builder().data(recordService.getTimeSlots(date, masterId)).build());
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Response<String>> create(@RequestHeader("authorization") String token, @RequestBody CreateRecordRequest dto) {
        recordService.createRecord(dto, tokenService.getPhoneFromToken(token.replace("Bearer ", "").trim()));
        return ResponseEntity.ok(Response.<String>builder().message("Вы успешно записались на прием").build());
    }

    @GetMapping("get-my-records")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Response<List<GetRecordResponse>>> getMyRecords(@RequestHeader("authorization") String token) {
        return ResponseEntity.ok(Response.<List<GetRecordResponse>>builder()
                .data(recordService.getUserRecords(tokenService.getPhoneFromToken(token.replace("Bearer ", "").trim())))
                .build());
    }
    @GetMapping("get-active-records")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<List<GetRecordResponse>>> getActiveRecords() {
        return ResponseEntity.ok(Response.<List<GetRecordResponse>>builder()
                .data(recordService.getActiveRecords())
                .build());
    }
    @DeleteMapping("delete/{recordId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Response<String>> delete(@PathVariable("recordId") Integer recordId) {
        recordService.deleteRecord(recordId);
        return ResponseEntity.ok(Response.<String>builder().message("Запись отменена").build());
    }
}
