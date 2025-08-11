package com.example.crispixmanusbackend.api.replicateAi.model.fluxKontext;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CreateFluxKontextRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -6111834359914641568L;


    private Input input;

    @Data
    public static class Input implements Serializable{
        @Serial
        private static final long serialVersionUID = 1405075809459425977L;
        /**
         * 提示词
         */
        private String prompt;

        /**
         * 生成图像的比例
         */
        private String aspect_ratio;

        /**
         * 图像url
         */
        private String input_image;

        /**
         * 生成格式
         */
        private String output_format="jpg";

        private Integer safety_tolerance=2;
    }





}
