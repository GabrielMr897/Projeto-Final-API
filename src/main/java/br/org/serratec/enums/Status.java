package br.org.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.serratec.exception.EnumValidationException;

public enum Status {
    FINALIZADO(1, "FINALIZADO"), NAO_FINALIZADO(2, "NAO FINALIZADO");

    private Integer id;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    Status(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    @JsonCreator
    public static Status verifica(Integer valor) throws EnumValidationException {
        for (Status status: Status.values()) {
            if(valor.equals(status.getId())) {
                return status;
            }
        }
        throw new EnumValidationException("Preencha o status corretamente");
    }
}
