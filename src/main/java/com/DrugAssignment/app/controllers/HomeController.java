package com.DrugAssignment.app.controllers;

import com.DrugAssignment.app.dto.CompositionDto;
import com.DrugAssignment.app.dto.DataDto;
import com.DrugAssignment.app.dto.IngredientDataDto;
import com.DrugAssignment.app.dto.IngredientDto;
import com.DrugAssignment.app.model.*;
import com.DrugAssignment.app.service.CompositionRepositoryService;
import com.DrugAssignment.app.service.CompositionService;
import com.DrugAssignment.app.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CompositionService compositionService;

    @PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<DataDto> addData(@RequestBody List<DataDto> dataDtoList){
        String response = compositionService.saveCompositions(dataDtoList);
        System.out.println(response);
        return dataDtoList;
    }

    @GetMapping(path = "get/composition")
    public ResponseEntity<CompositionDto> getCompositionById(@RequestParam("id") int id){
        CompositionDto compositionDto = compositionService.getCompositionById(id);
        return new ResponseEntity<>(compositionDto, HttpStatus.OK);
    }

    @GetMapping(path = "getAll/composition")
    public ResponseEntity<List<CompositionDto>> getCompositionByIngredientStrengthUnit(@RequestParam("id") int id, @RequestParam("strength") float strength, @RequestParam("unit") String unit){
        List<CompositionDto> compositionDtoList = compositionService.getCompositionByIngredientStrengthUnit(id, strength, unit);
        return new ResponseEntity<>(compositionDtoList, HttpStatus.OK);
    }

}
