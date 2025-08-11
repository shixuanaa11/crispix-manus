package com.example.crispixmanusbackend.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DeleteRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 8604442280346411050L;

    private Long id;
}
