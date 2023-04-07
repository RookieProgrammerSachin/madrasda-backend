package com.example.madrasdaapi.controllers.common;

import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Size;
import com.example.madrasdaapi.repositories.ColorRepository;
import com.example.madrasdaapi.repositories.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/colorsAndSizes/")
public class ColorAndSizeController {
     private final ColorRepository colorRepository;
     private final SizeRepository sizeRepository;

     @GetMapping("getColorsAndSizes")
     public HashMap<String, List> getColorsAndSizes() {
          HashMap<String, List> data = new HashMap<>();
          data.put("colors", (colorRepository.findAll()));
          data.put("sizes",(sizeRepository.findAll()));
          return data;
     }

     @PostMapping("addColor")
     public Color addColor(@RequestBody Color color) {
          return colorRepository.save(color);
     }

     @PutMapping("updateColor")
     public Color updateColor(@RequestBody Color color) {
          return colorRepository.save(color);

     }

     @DeleteMapping("deleteColor/{id}")
     public void deleteColor(@PathVariable Long id) {
          colorRepository.deleteById(id);
     }

     @PostMapping("/addSize")
     public Size addSize(@RequestBody Size size) {
          return sizeRepository.save(size);
     }

     @PutMapping("/updateSize")
     public Size updateSize(@RequestBody Size size) {
          return sizeRepository.save(size);
     }


     @DeleteMapping("deleteSize/{id}")
     public void deleteSize(@PathVariable Long id) {
          sizeRepository.deleteById(id);
     }
}
