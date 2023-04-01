package com.example.madrasdaapi.services.commons;

import com.example.madrasdaapi.dto.commons.FeedbackDTO;
import com.example.madrasdaapi.dto.commons.FeedbackPage;
import com.example.madrasdaapi.mappers.FeedbackMapper;
import com.example.madrasdaapi.models.Feedback;
import com.example.madrasdaapi.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
     private final FeedbackRepository feedbackRepository;
     private final FeedbackMapper feedbackMapper;

     public FeedbackPage getAllQueries(int pageNo, int pageSize){
          Sort sort = Sort.by("timestamp").descending();
          PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
          Page<FeedbackDTO> unresolvedQueries = feedbackRepository.findByResolution(false, pageRequest).map(feedbackMapper::mapToDTO);
          Page<FeedbackDTO> resolvedQueries = feedbackRepository.findByResolution(true, pageRequest).map(feedbackMapper::mapToDTO);
          return new FeedbackPage(unresolvedQueries, resolvedQueries);


     }

     public FeedbackDTO saveOrUpdate(FeedbackDTO feedbackDTO) {
          Feedback detachedFeedback = feedbackMapper.mapToEntity(feedbackDTO);
          return feedbackMapper.mapToDTO(feedbackRepository.save(detachedFeedback));
     }

     public void toggleQuery(Long id) {
          Feedback feedback = feedbackRepository.findById(id).get();
          feedback.setResolution(!feedback.getResolution());
          feedbackRepository.save(feedback);
     }
}
