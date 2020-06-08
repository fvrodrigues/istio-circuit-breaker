package br.com.votorantim.istio.backend.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sample")
@Slf4j
public class SampleController {

    @GetMapping(value = "/success")
    public ResponseEntity getSuccess(){
        log.info("Acesso ao getSuccess");
        return ResponseEntity.ok(new Response(1, "success"));
    }

    @GetMapping(value = "/client-error")
    public ResponseEntity getErrorClient(){
        log.info("Acesso ao getErrorClient");
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/server-error")
    public ResponseEntity getErrorServer(){
        log.info("Acesso ao getErrorClient");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value = "/success-slow")
    @SneakyThrows
    public ResponseEntity getSlowResponse(){
        log.info("Acesso ao getSlowResponse");
        Thread.sleep(1000);
        log.info("Retornando depois da longa espera");
        return ResponseEntity.ok(new Response(2, "slow"));
    }


    @AllArgsConstructor
    class Response {
        public Integer id;
        public String message;
    }

}
