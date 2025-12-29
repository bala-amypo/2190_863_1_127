package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.demo.entity.ComplaintStatus;
import com.example.demo.service.ComplaintStatusService;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "*")
public class StatusController{

    @Autowired
        ComplaintStatusService service;

            @GetMapping("/history/{id}")
                public List<ComplaintStatus> getComplaintStatus(@PathVariable Long id){
                        return service.getComplaintStatus(id);
                            }
                            }
                            