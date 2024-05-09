package com.tobeto.lms.controllers;

import com.tobeto.lms.services.abstracts.ReturnService;
import com.tobeto.lms.services.dtos.requests.AddReturnRequest;
import com.tobeto.lms.services.dtos.responses.AddReturnResponse;
import com.tobeto.lms.services.dtos.responses.ListReturnResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/returns")
public class ReturnController {
    private ReturnService returnService;

    @GetMapping
    public List<ListReturnResponse> getAll(){
        return returnService.getAll();
    }

    @PostMapping
    public AddReturnResponse add(@RequestBody @Valid AddReturnRequest request){
        return returnService.add(request);
    }
}
