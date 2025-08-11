package com.example.crispixmanusbackend.api.replicateAi;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;

import com.example.crispixmanusbackend.api.replicateAi.model.fluxKontext.CreateFluxKontextRequest;
import com.example.crispixmanusbackend.api.replicateAi.model.fluxKontext.GetFluxKontextRequest;
import com.example.crispixmanusbackend.common.ErrorCode;
import com.example.crispixmanusbackend.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * author: axuan
 * replicate平台门面类，统一封装该平台的api
 */
@Slf4j
@Component
public class ReplicatedAiApi {

    //  Replicate API Token
    @Value("${replicate-api.token}")
    private String REPLICATE_API_TOKEN;

    //     图像智能编辑 flux kontext pro
    public static final String FLUX_KONTEXT_PRO_URL = "https://api.replicate.com/v1/models/black-forest-labs/flux-kontext-pro/predictions";



    //  -------------------------  智能改图 flux kontext ----------------------------
    public GetFluxKontextRequest getfluxkontextproRequest(CreateFluxKontextRequest createFluxKontextRequest) {
        // 发送 POST 请求
        HttpRequest httpRequest = HttpRequest.post(FLUX_KONTEXT_PRO_URL)
                .header(Header.AUTHORIZATION, "Bearer " + REPLICATE_API_TOKEN)
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .header("Prefer", "wait")
                .body(JSONUtil.toJsonStr(createFluxKontextRequest));

        try (HttpResponse httpResponse = httpRequest.execute()) {
            if (!httpResponse.isOk()) {
                log.error("请求异常：{}", httpResponse.body());
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "flux kontext失败");
            }
            GetFluxKontextRequest response = JSONUtil.toBean(httpResponse.body(), GetFluxKontextRequest.class);
            String errorCode = response.getError();
            if (StrUtil.isNotBlank(errorCode)) {

                log.error("flux kontext，errorCode:{}, errorMessage:{}", errorCode);
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "flux kontext失败");
            }
            return response;
        }
    }



}
