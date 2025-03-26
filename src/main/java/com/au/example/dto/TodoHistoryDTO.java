package com.au.example.dto;

import java.time.Instant;

public record TodoHistoryDTO(Long id, String title, boolean done, Instant createdOn, Instant lastUpdatedOn) {

}