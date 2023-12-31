package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.dto.commons.FeedbackDTO;
import com.example.madrasdaapi.dto.commons.FeedbackPage;
import com.example.madrasdaapi.services.commons.FeedbackService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback/")
@Tag(name = "Feedback Resource Controller")
@CrossOrigin

public class FeedbackController {
     private final FeedbackService feedbackService;

     public FeedbackController(FeedbackService feedbackService) {
          this.feedbackService = feedbackService;
     }

     @ResponseStatus(HttpStatus.CREATED)
     @PostMapping("postFeedback")
     public FeedbackDTO postFeedback(@RequestBody FeedbackDTO feedbackDTO) {
          return feedbackService.saveOrUpdate(feedbackDTO);
     }

     @GetMapping("getAllQueries")
     public FeedbackPage getAllQueries(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
          return feedbackService.getAllQueries(pageNo, pageSize);
     }

     @PutMapping("toggleFeedbackResolution/{id}")
     public void resolveQuery(@PathVariable Long id) {
          feedbackService.toggleQuery(id);
     }

     @DeleteMapping("deleteFeedback/{id}")
     public void deleteFeedbackById(@PathVariable Long id) {
          feedbackService.deleteById(id);
     }
}
