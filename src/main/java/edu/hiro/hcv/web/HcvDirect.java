package edu.hiro.hcv.web;

import org.springframework.stereotype.Controller;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Controller
public class HcvDirect {

  @ExtDirectMethod
  public long multiply(long num) {
    return num * 8;
  }
}
