package com.example.madrasdaapi.dto.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class FeedbackPage {
     Page<FeedbackDTO> unresolvedQueries;
     Page<FeedbackDTO> resolvedQueries;
}
