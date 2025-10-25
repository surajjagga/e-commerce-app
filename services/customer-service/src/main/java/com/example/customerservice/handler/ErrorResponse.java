package com.example.customerservice.handler;

import java.util.HashMap;
import java.util.Map;

public record ErrorResponse (HashMap<String,String> errors){
}
