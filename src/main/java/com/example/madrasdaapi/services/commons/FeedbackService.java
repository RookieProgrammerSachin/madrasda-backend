package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.dto.commons.FeedbackDTO;
import com.example.madrasdaapi.dto.commons.FeedbackPage;
import com.example.madrasdaapi.mappers.FeedbackMapper;
import com.example.madrasdaapi.mappers.VendorMapper;
import com.example.madrasdaapi.models.Feedback;
import com.example.madrasdaapi.repositories.FeedbackRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
     private final FeedbackRepository feedbackRepository;
     private final VendorRepository vendorRepository;
     private final VendorMapper vendorMapper;
     private final FeedbackMapper feedbackMapper;
     public FeedbackService(FeedbackRepository feedbackRepository, VendorRepository vendorRepository, VendorMapper vendorMapper, FeedbackMapper feedbackMapper) {
          this.feedbackRepository = feedbackRepository;
          this.vendorRepository = vendorRepository;
          this.vendorMapper = vendorMapper;
          this.feedbackMapper = feedbackMapper;
     }

     public FeedbackPage getAllQueries(int pageNo, int pageSize){
          Sort sort = Sort.by("timestamp").descending();
          PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
          Page<FeedbackDTO> unresolvedQueries = feedbackRepository.findByResolution(false, pageRequest).map(feedbackMapper::mapToDTO);
          Page<FeedbackDTO> resolvedQueries = feedbackRepository.findByResolution(true, pageRequest).map(feedbackMapper::mapToDTO);
          return new FeedbackPage(unresolvedQueries, resolvedQueries);


     }

     public FeedbackDTO createQuery(FeedbackDTO feedbackDTO) {
          Feedback detachedFeedback = feedbackMapper.mapToEntity(feedbackDTO);
          return feedbackMapper.mapToDTO(feedbackRepository.save(detachedFeedback));
     }

     public void toggleQuery(Long id) {
          Feedback feedback = feedbackRepository.findById(id).get();
          feedback.setResolution(!feedback.getResolution());
          feedbackRepository.save(feedback);
     }
}
