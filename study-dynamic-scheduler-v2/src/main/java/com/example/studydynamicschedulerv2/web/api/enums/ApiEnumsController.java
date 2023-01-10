package com.example.studydynamicschedulerv2.web.api.enums;

import com.example.studydynamicschedulerv2.enums.EnumMapperValue;
import com.example.studydynamicschedulerv2.enums.scheduler.ExecuteType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enums")
public class ApiEnumsController {

    @GetMapping("execute-type")
    public ResponseEntity<List<EnumMapperValue>> executeType() {
        return new ResponseEntity<>(Arrays.stream(ExecuteType.values()).map(EnumMapperValue::new).collect(Collectors.toList()), HttpStatus.OK);
    }

}
