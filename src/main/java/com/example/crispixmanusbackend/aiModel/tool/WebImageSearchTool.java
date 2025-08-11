package com.example.crispixmanusbackend.aiModel.tool;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
// * 联网搜索
 * {
 *   "images": [
 *     {
 *       "position": 1,
 *       "title": "Giant panda - Wikipedia",
 *       "source": {
 *         "name": "Wikipedia",
 *         "link": "https://en.wikipedia.org/wiki/Giant_panda"
 *       },
 *       "original": {
 *         "link": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0f/Grosser_Panda.JPG/640px-Grosser_Panda.JPG",
 *         "width": 640,
 *         "height": 427
 *       },
 *       "thumbnail": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcizVg6CO7Hd_xCwzi3buwc7qVN6MnPUciSw&s"
 *     },
 *     ...
 *   ]
 * }
 */
@Slf4j
public class WebImageSearchTool {

    // SearchAPI 的搜索接口地址
    private static final String SEARCH_API_URL = "https://www.searchapi.io/api/v1/search";

    private final String apiKey ;

    public WebImageSearchTool(String apiKey) {
        this.apiKey = apiKey;
    }

    @Tool(description = "Search for image from Google Image Search Engine")
    public String searchWeb(
            @ToolParam(description = "Search query keyword") String query) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("q", query);
        paramMap.put("api_key", apiKey);
        paramMap.put("engine", "google_images");
        paramMap.put("h1","zh-cn");

        try {
            String response = HttpUtil.get(SEARCH_API_URL, paramMap);

            // 取出返回结果的前 10 条
            JSONObject jsonObject = JSONUtil.parseObj(response);
            JSONArray organicResults = jsonObject.getJSONArray("images");
            List<Object> objects = organicResults.subList(0, Math.min(10, organicResults.size()));
            StringBuilder sb = new StringBuilder();
            sb.append("本次共找到图片 ").append(objects.size()).append(" 张，图片如下:");
            int idx = 1;
            for (Object obj : objects) {
                JSONObject tmpJSONObject = (JSONObject) obj;
                JSONObject original = tmpJSONObject.getJSONObject("original");
                String imageUrl = original.getStr("link");
                sb.append(idx).append(". ").append(imageUrl).append("\n");
                idx++;
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error searching google Image Search: " + e.getMessage();
        }
    }
}
