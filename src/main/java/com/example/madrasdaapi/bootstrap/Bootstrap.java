package com.example.madrasdaapi.bootstrap;

import com.example.madrasdaapi.models.Feedback;
import com.example.madrasdaapi.models.User;
import com.example.madrasdaapi.models.Vendor;
import com.example.madrasdaapi.repositories.FeedbackRepository;
import com.example.madrasdaapi.repositories.UserRepository;
import com.example.madrasdaapi.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class Bootstrap implements CommandLineRunner {
     private final VendorRepository vendorRepository;
     private final FeedbackRepository feedbackRepository;

     public Bootstrap(UserRepository userRepository, VendorRepository vendorRepository,
                      FeedbackRepository feedbackRepository) {
          this.vendorRepository = vendorRepository;
          this.feedbackRepository = feedbackRepository;
     }

     @Override
     public void run(String... args) throws Exception {
          int x = 100;
          boolean resolution = true;
          List<Vendor> vendors = vendorRepository.findAll();
          for(Vendor vendor: vendors){
               Feedback feedback = new Feedback();
               feedback.setVendor(vendor);
               feedback.setQuery("There " + x-- + " bottles lying around you take one down and pass around");
               feedback.setResolution(resolution = !resolution);
               feedbackRepository.save(feedback);
          }


     }
}
