package com.example.alticelabs2.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LabSeqController {
    /*
    Hash Map, to save the already calculated values of labseq function.
    The key will be the n's, and the values will be the labseq result
    for that n.
     */
    HashMap<Integer, Long> cache = new HashMap<Integer, Long>();




    @GetMapping("/labseq/{n}")
    @Operation(summary = "Get labsec function result.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Calculation done."),
            @ApiResponse(responseCode = "400", description = "Invalid Input. N must be an Integer >=0.")
    })
    /*
    Function that receives requests to the get labseq value endpoint.
    It calls the calculateLabSeqFunction and return its value in a String.
     */
    public String labSeq(
            @Parameter(description = "The integer value of n to use for the labsec calculation. Must be >= 0.")
            @PathVariable String n) {
        try {
            int value = Integer.parseInt(n);
            if (value < 0) {
                throw new IllegalArgumentException("n must be greater than or equal to 0");
            }
            return calculateLabSeq(value).toString();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Error> handleResponseStatusException(ResponseStatusException ex) {
        Error error = new Error(ex.getStatus().value(), ex.getMessage());;
        return new ResponseEntity<>(error, ex.getStatus());
    }


    /*
    Function that do the calculation of the labsec
    It receives a Positive Integer Value - n.
     */
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


