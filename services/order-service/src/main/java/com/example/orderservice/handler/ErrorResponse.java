package com.example.orderservice.handler;

import java.util.HashMap;

public record ErrorResponse(HashMap<String,String> errors){
}
