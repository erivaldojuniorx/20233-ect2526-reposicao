package br.ufrn.ect.rastreador.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseObject {
    private int status;
    private String message;
}
