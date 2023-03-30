package com.example.madrasdaapi.mappers;

import com.example.madrasdaapi.dto.commons.FeedbackDTO;
import com.example.madrasdaapi.models.Feedback;
import com.example.madrasdaapi.repositories.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackMapper {
     private final VendorMapper vendorMapper;
     private final VendorRepository vendorRepository;

     public FeedbackMapper(VendorMapper vendorMapper, VendorRepository vendorRepository) {
          this.vendorMapper = vendorMapper;
          this.vendorRepository = vendorRepository;
     }

     public FeedbackDTO mapToDTO(Feedback feedback) {
          FeedbackDTO feedbackDTO = new FeedbackDTO();
          feedbackDTO.setId(feedback.getId());
          feedbackDTO.setResolution(feedback.getResolution());
          feedbackDTO.setQuery(feedback.getQuery());
          feedbackDTO.setTimestamp(feedback.getTimestamp());
          feedbackDTO.setVendorDTO(vendorMapper.mapToDTO(feedback.getVendor()));
          return feedbackDTO;
     }

     public Feedback mapToEntity(FeedbackDTO feedbackDTO) {
          Feedback feedback = new Feedback();
          feedback.setId(feedbackDTO.getId());
          feedback.setResolution(feedbackDTO.getResolution());
          feedback.setTimestamp(feedbackDTO.getTimestamp());
          feedback.setQuery(feedbackDTO.getQuery());
          feedback.setVendor(vendorRepository.findById(feedbackDTO.getVendorDTO().getId()).get());

          return feedback;
     }
}
