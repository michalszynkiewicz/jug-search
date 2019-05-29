package com.example;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * <br>
 * Date: 28/05/2019
 */
@ApplicationScoped
public class QueryEmitter {

   @Stream("queries")
   Emitter<String> queryEmitter;

   public void send(String message) {
      queryEmitter.send(message);
   }
}
