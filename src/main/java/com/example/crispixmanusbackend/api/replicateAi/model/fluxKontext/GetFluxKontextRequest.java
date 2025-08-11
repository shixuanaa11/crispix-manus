package com.example.crispixmanusbackend.api.replicateAi.model.fluxKontext;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class GetFluxKontextRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -7851610376483472103L;

    private Date completedAt;
    private Date createdAt;
    private boolean dataRemoved;
    private String error;
    private String id;
    private Input input;
    private String logs;
    private Metrics metrics;
    private String output;
    private Date startedAt;
    private String status;
    private Urls urls;
    private String version;



@Data
public static class Input {
    private String prompt;
    private Boolean go_fast;
    private Double guidance;
    private Integer num_outputs;
    private String aspect_ratio;
    private String output_format;
    private Integer output_quality;
    private Double prompt_strength;
    private Integer num_inference_steps;
}

@Data
public static class Metrics {
    private Integer image_count;
    private Double predict_time;
    private Double total_time;
}

@Data
public static class Urls {
    private String get;
    private String cancel;
  }

}