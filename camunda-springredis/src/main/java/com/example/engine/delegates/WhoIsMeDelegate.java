package com.example.engine.delegates;

import com.example.engine.utils.WhoIsMeUtil;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class WhoIsMeDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    delegateExecution.setVariable("whoIsMe", WhoIsMeUtil.whoIsMe());
  }

}
