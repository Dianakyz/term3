package com.example.term3.service;

import com.example.term3.dto.SockRequest;
import com.example.term3.model.Color;
import com.example.term3.model.Size;
import com.example.term3.model.Sock;
import org.springframework.stereotype.Service;

@Service
public interface SockService {
    void addSock(SockRequest sockRequest);

    void issueSock(SockRequest sockRequest);

    void decreaseSockQuantity(SockRequest sockRequest);

    int getSockQuantity (Color color, Size size, Integer cottonMin, Integer cottonMax);

    void validateRequest(SockRequest sockRequest);

    Sock mapToSock(SockRequest sockRequest);

    void removeDefectiveSocks(SockRequest sockRequest);
}
