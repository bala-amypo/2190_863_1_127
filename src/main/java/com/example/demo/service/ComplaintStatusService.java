package com.example.demo.service;

import java.util.*;
import com.example.demo.entity.ComplaintStatus;

public interface ComplaintStatusService{
    public List<ComplaintStatus> getComplaintStatus(Long id);
}