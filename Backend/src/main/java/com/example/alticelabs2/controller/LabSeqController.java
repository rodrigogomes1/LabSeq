package com.example.alticelabs2.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import java.util.HashMap;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LabSeqController {
    HashMap<Integer, Long> cache = new HashMap<Integer, Long>();


    @GetMapping("/labseq/{n}")
    @Operation(summary = "Get labsec function result.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculation done.")
    })
    public String labSeq(
            @Parameter(description = "The integer value of n to use for the labsec calculation. Must be > 0.")
            @PathVariable Integer n) {
        return calculateLabSeq(n).toString();
    }


    private Long calculateLabSeq(Integer n) {

        if (cache.containsKey(n)){
            return cache.get(n);
        }

        Long result = 0L;
        if(n==1 || n ==3){
            result= 1L;
        } else if (n>3){
            int first= n-4;
            int second= n-3;

            Long lFirst = cache.containsKey(first) ? cache.get(first) : calculateLabSeq(first);
            Long lSecond = cache.containsKey(second) ? cache.get(second) : calculateLabSeq(second);

            result=lFirst+lSecond;
        }

        cache.put(n,result);
        return result;
    }
}


