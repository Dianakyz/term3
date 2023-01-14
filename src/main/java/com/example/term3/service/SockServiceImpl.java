package com.example.term3.service;

import com.example.term3.dto.SockRequest;
import com.example.term3.exception.InSufficientSockQuantityException;
import com.example.term3.exception.InvalidSockException;
import com.example.term3.model.Color;
import com.example.term3.model.Size;
import com.example.term3.model.Sock;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SockServiceImpl implements SockService {

    private final Map<Sock, Integer> socks = new HashMap<>();

    @Override
    public void addSock(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        if (socks.containsKey(sock)) {
            socks.put(sock, socks.get(sock) + sockRequest.getQuantity());
        } else {
            socks.put(sock, sockRequest.getQuantity());
        }
    }

    @Override
    public void issueSock(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }

    @Override
    public void removeDefectiveSocks(SockRequest sockRequest) {
        decreaseSockQuantity(sockRequest);
    }

    @Override
    public void decreaseSockQuantity(SockRequest sockRequest) {
        validateRequest(sockRequest);
        Sock sock = mapToSock(sockRequest);
        int sockQuantity = socks.getOrDefault(sock, 0);
        if (sockQuantity >= sockRequest.getQuantity()) {
            socks.put(sock, sockQuantity - sockRequest.getQuantity());
        } else {
            throw new InSufficientSockQuantityException("Здесь уже нет носков");
        }
    }

    @Override
    public int getSockQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int total = 0;
        for(Map.Entry<Sock, Integer> entry : socks.entrySet()) {
            if (color != null && !entry.getKey().getColor().equals(color)) {
                continue;
            }
            if (size != null && !entry.getKey().getSize().equals(size)) {
                continue;
            }
            if (cottonMin != null && entry.getKey().getCottonPersentage() < cottonMin) {
                continue;
            }
            if (cottonMax != null && entry.getKey().getCottonPersentage() > cottonMax) {
                continue;
            }
            total += entry.getValue();
        }
        return total;
    }

    @Override
    public void validateRequest(SockRequest sockRequest) {
        if (sockRequest.getColor() == null && sockRequest.getSize() == null) {
            throw new InvalidSockException("Все поля должны быть заполнены");
        }
        if (sockRequest.getCottonPercentage() < 0 || sockRequest.getCottonPercentage() > 100) {
            throw new InvalidSockException("Процент хлопка необоходимо указать в диапазоне от 0 до 100");
        }
        if (sockRequest.getQuantity() <= 0) {
            throw new InvalidSockException("Колличество не может быть меньше 0");
        }
    }

    @Override
    public Sock mapToSock(SockRequest sockRequest) {
        Sock sock = new Sock(sockRequest.getColor(), sockRequest.getSize(), sockRequest.getCottonPercentage());
        return sock;
    }


}
