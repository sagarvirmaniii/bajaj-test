package com.bhfl.controller;

import com.bhfl.dto.BfhlRequest;
import com.bhfl.dto.BfhlResponse;
import com.bhfl.service.BfhlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bfhl")
@RequiredArgsConstructor
@Tag(name = "BFHL", description = "Chitkara Qualifier 1 API")
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping
    @Operation(summary = "Process BFHL input data")
    public ResponseEntity<BfhlResponse> process(@Valid @RequestBody BfhlRequest request) {
        return ResponseEntity.ok(bfhlService.process(request));
    }
}
