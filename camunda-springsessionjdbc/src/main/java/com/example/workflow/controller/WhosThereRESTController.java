package com.example.workflow.controller;

import com.example.workflow.utils.WhoIsMeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WhosThereRESTController {

  @GetMapping("/whosThere")
  public String canGetOnlyWithRequiredHeaders() {
    return WhoIsMeUtil.whoIsMe();
  }

}