package com.example.madrasdaapi.services.VendorServices;

import com.example.madrasdaapi.dto.VendorDTO.FeedbackDTO;
import com.example.madrasdaapi.models.Feedback;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
     public FeedbackDTO mapToDTO(Feedback feedback) {
          FeedbackDTO feedbackDTO = new FeedbackDTO();
          feedbackDTO.setId(feedback.getId());
          feedbackDTO.setResolution(feedback.getResolution());
          feedbackDTO.setQuery(feedback.getQuery());

          return feedbackDTO;
     }

     public Feedback mapToEntity(FeedbackDTO feedbackDTO) {
          Feedback feedback = new Feedback();
          feedback.setId(feedbackDTO.getId());
          feedback.setResolution(feedbackDTO.getResolution());
          feedback.setQuery(feedbackDTO.getQuery());

          return feedback;
     }
}
