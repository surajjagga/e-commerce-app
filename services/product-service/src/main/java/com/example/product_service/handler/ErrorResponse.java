package com.example.product_service.handler;

import java.util.HashMap;

public record ErrorResponse(HashMap<String,String> errors){
}
