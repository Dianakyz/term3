package com.example.term3.contoller;

import com.example.term3.dto.SockRequest;
import com.example.term3.exception.InSufficientSockQuantityException;
import com.example.term3.exception.InvalidSockException;
import com.example.term3.model.Color;
import com.example.term3.model.Size;
import com.example.term3.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {

    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @ExceptionHandler(InvalidSockException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockException invalidSockException) {
        return ResponseEntity.badRequest().body(invalidSockException.getMessage());
    }

    @ExceptionHandler(InSufficientSockQuantityException.class)
    public ResponseEntity<String> handleInSufficientException(InSufficientSockQuantityException inSufficientSockQuantityException) {
        return ResponseEntity.badRequest().body(inSufficientSockQuantityException.getMessage());
    }

    @PostMapping
    public void addSock(@RequestBody SockRequest sockRequest) {
        sockService.addSock(sockRequest);
    }

    @PutMapping
    public void issueSocks(@RequestBody SockRequest sockRequest) {
        sockService.issueSock(sockRequest);
    }

    @GetMapping
    public int getSocksCount (@RequestParam(required = false, name = "color") Color color,
                              @RequestParam(required = false, name = "size") Size size,
                              @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                              @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
        sockService.removeDefectiveSocks(sockRequest);
    }
}
