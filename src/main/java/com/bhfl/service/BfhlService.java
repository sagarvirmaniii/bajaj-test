package com.bhfl.service;

import com.bhfl.dto.BfhlRequest;
import com.bhfl.dto.BfhlResponse;

public interface BfhlService {

    BfhlResponse process(BfhlRequest request);
}
