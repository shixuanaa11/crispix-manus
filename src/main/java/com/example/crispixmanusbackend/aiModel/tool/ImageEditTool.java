package com.example.crispixmanusbackend.aiModel.tool;


import com.example.crispixmanusbackend.api.replicateAi.ReplicatedAiApi;
import com.example.crispixmanusbackend.api.replicateAi.model.fluxKontext.CreateFluxKontextRequest;
import com.example.crispixmanusbackend.api.replicateAi.model.fluxKontext.GetFluxKontextRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * author: axuan
 * 图片智能改图工具 Flux Kontext pro
 */
@Slf4j
public class ImageEditTool {


    @Tool(description = " An advanced AI-powered image editing tool that performs semantic-aware modifications based on natural language instructions. Supports style transfer, object manipulation, text editing, and inpainting/outpainting. Always verify edit feasibility and request clarifications for ambiguous prompts.", returnDirect = false)
    public String ImageAIAdvancedEdit(
            @ToolParam(description = "Image to use as reference. Must be jpeg, png, gif, or webp") String imageUrl,
            @ToolParam(description = "Format: String (English only)\n" +
                    "\n" +
                    "Description: Text description of what you want to generate, or the instruction on how to edit the given image:\n" +
                    "\n" +
                    "Target elements (e.g., \"the red car\")\n" +
                    "\n" +
                    "Action (e.g., \"remove\", \"recolor to blue\")\n" +
                    "\n" +
                    "Style references (e.g., \"in Van Gogh style\")\n" +
                    "\n" +
                    "Example: \"Remove the background text, make the dog wear a pirate hat, and apply oil painting effect\"") String imagePrompt,
            @ToolParam(description = "Aspect ratio of the generated image. Use 'match_input_image' to match the aspect ratio of the input image.Default: \"match_input_image\",if the quizzer did not indicate the specific size or Aspect ratio,you must follow the Default." +
                    "Options:\n" +
                    "\n" +
                    "\"match_input_image\" (default, preserves original aspect ratio)\n" +
                    "\n" +
                    "\"1:1\" (square)\n" +
                    "\n" +
                    "\"16:9\" (widescreen)\n" +
                    "\n" +
                    "\"9:16\" (vertical/portrait)\n" +
                    "\n" +
                    "\"4:3\" (standard monitor)\n" +
                    "\n" +
                    "\"3:4\" (vertical)\n" +
                    "\n" +
                    "\"3:2\" (classic photo)\n" +
                    "\n" +
                    "\"2:3\" (vertical photo)\n" +
                    "\n" +
                    "\"4:5\" (portrait photo)\n" +
                    "\n" +
                    "\"5:4\" (slightly taller)\n" +
                    "\n" +
                    "\"21:9\" (ultrawide)\n" +
                    "\n" +
                    "\"9:21\" (ultra-tall)\n" +
                    "\n" +
                    "\"2:1\" (panoramic)\n" +
                    "\n" +
                    "\"1:2\" (vertical panoramic)" )String aspect_ratio) {

        // 封装请求参数
        try {
            CreateFluxKontextRequest createFluxKontextRequest = new CreateFluxKontextRequest();
            CreateFluxKontextRequest.Input input = new CreateFluxKontextRequest.Input();
            input.setAspect_ratio(aspect_ratio);
            input.setPrompt(imagePrompt);
            input.setInput_image(imageUrl);
            createFluxKontextRequest.setInput(input);

            ReplicatedAiApi replicatedAiApi = new ReplicatedAiApi();
            // 传给工具类调用api
            GetFluxKontextRequest getFluxKontextRequest = replicatedAiApi.getfluxkontextproRequest(createFluxKontextRequest);
            // 提取出图片
            String outputImage = getFluxKontextRequest.getOutput();
            return "image editing successfully to the URL: " + outputImage;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);

        }
    }
}