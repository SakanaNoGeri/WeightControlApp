package com.example.weightcontrolapp.controller;

import com.example.weightcontrolapp.dto.response.DailyReportResponse;
import com.example.weightcontrolapp.dto.response.HistoryResponse;
import com.example.weightcontrolapp.dto.response.NormCheckResponse;
import com.example.weightcontrolapp.dto.response.commonResponse.CustomSuccessResponse;
import com.example.weightcontrolapp.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily/{userId}")
    public ResponseEntity<CustomSuccessResponse<DailyReportResponse>> getDailyReport(
            @PathVariable UUID userId,
            @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(reportService.getDailyReport(userId, date)));
    }

    @GetMapping("/check-norm/{userId}")
    public ResponseEntity<CustomSuccessResponse<NormCheckResponse>> checkDailyNorm(
            @PathVariable UUID userId,
            @RequestParam("date") LocalDate date) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(reportService.checkDailyNorm(userId, date)));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<CustomSuccessResponse<HistoryResponse>> getMealHistory(
            @PathVariable UUID userId) {
        return ResponseEntity.ok(new CustomSuccessResponse<>(reportService.getMealHistory(userId)));
    }
}
