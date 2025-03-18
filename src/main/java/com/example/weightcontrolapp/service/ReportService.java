package com.example.weightcontrolapp.service;

import com.example.weightcontrolapp.dto.response.DailyReportResponse;
import com.example.weightcontrolapp.dto.response.HistoryResponse;
import com.example.weightcontrolapp.dto.response.NormCheckResponse;
import java.time.LocalDate;
import java.util.UUID;

public interface ReportService {

    DailyReportResponse getDailyReport(UUID userId, LocalDate date);

    NormCheckResponse checkDailyNorm(UUID userId, LocalDate date);

    HistoryResponse getMealHistory(UUID userId);
}
