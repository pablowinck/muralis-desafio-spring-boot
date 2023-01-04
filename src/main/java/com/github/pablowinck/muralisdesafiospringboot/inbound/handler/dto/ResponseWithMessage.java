package com.github.pablowinck.muralisdesafiospringboot.inbound.handler.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseWithMessage {

	private String codigo;

	private String mensagem;

}
