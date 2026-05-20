package com.terranova.api.v1.user.infrastructure.adapter.in.web.controller;

import com.terranova.api.v1.user.application.usecase.FindUserCaseUse;
import com.terranova.api.v1.user.infrastructure.adapter.in.web.dto.SellerSummaryResponse;
import com.terranova.api.v1.user.infrastructure.adapter.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final FindUserCaseUse findUserCaseUse;
    private final UserMapper userMapper;

    @PostMapping("/internal/batch")
    public ResponseEntity<Map<UUID, SellerSummaryResponse>> batchSellerSummary(@Valid @RequestBody List<UUID> ids){
        return ResponseEntity.ok(
                findUserCaseUse.findSellerSummary(ids)
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> userMapper.domainSellerSummaryToSellerSummaryResponse(entry.getValue())
                        ))
        );
    }
}
