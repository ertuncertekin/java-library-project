package com.tobeto.lms.services.abstracts;

import com.tobeto.lms.services.dtos.requests.AddReturnRequest;
import com.tobeto.lms.services.dtos.responses.AddReturnResponse;
import com.tobeto.lms.services.dtos.responses.ListReturnResponse;

import java.util.List;

public interface ReturnService {
    AddReturnResponse add(AddReturnRequest addReturnRequest);

    List<ListReturnResponse> getAll();
}
