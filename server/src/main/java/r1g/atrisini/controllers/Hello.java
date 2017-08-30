package r1g.atrisini.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/hello")
public class Hello {

  @RequestMapping(method = RequestMethod.GET, value="/{id}")
  public HashMap test(@PathVariable String id, @RequestParam("data") String data) {
    HashMap result = new HashMap();
    result.put("message", "Hello, id is: " + id);
    result.put("data", data);
    return result;
  }  
}
