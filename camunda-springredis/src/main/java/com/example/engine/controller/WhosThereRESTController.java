package com.example.engine.controller;

import com.example.engine.utils.WhoIsMeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhosThereRESTController {

  @GetMapping("/whosThere")
  public String canGetOnlyWithRequiredHeaders() {
    return WhoIsMeUtil.whoIsMe();
  }

}