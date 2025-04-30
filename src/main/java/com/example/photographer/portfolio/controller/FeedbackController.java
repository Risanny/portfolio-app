package com.example.photographer.portfolio.controller;

import com.example.photographer.portfolio.dto.FeedbackDto;
import com.example.photographer.portfolio.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Принимает POST-запрос с JSON-телом типа FeedbackDto,
     * отправляет письмо через FeedbackService и возвращает 200 OK.
     */
    @PostMapping
    public ResponseEntity<String> submitFeedback(@RequestBody FeedbackDto dto) {
        feedbackService.sendFeedback(dto);
        return new ResponseEntity<>("Feedback sent", HttpStatus.OK);
    }
}
