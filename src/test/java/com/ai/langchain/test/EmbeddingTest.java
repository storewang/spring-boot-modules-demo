package com.ai.langchain.test;

import com.ai.langchain.model.PersonalityTrait;
import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.filter.MetadataFilterBuilder;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/1
 **/
@SpringBootTest
public class EmbeddingTest {
    @Autowired
    private OpenAiEmbeddingModel embeddingModel;
    @Autowired
    private QdrantClient qdrantClient;
    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;
    @Autowired
    private EmbeddingModelTextClassifier<PersonalityTrait> textClassifier;

    @Test
    void embeddingModel() {
        Response<Embedding> embeddingResponse = embeddingModel.embed("测试文本，文本向量化");
        System.out.println(embeddingResponse);
    }

    @Test
    void createCollection() {
        var vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(1024)
                .build();
        qdrantClient.createCollectionAsync("testv", vectorParams);
    }

    @Test
    void testText() {
        TextSegment segment1 = TextSegment.from("浏览器报错 404，请检测您输入的路径是否正确");
        segment1.metadata().put("author", "冷冷");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);
    }

    @Test
    void testQuery1(){
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);
        System.out.println(searchResult.matches().get(0).embedded().text());
    }

    @Test
    void testQuery2(){
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .filter(MetadataFilterBuilder.metadataKey("author").isEqualTo("冷冷"))
                .maxResults(1)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);

        System.out.println(searchResult.matches());
    }

    @Test
    void textClassifier() {
        List<PersonalityTrait> personalityTraitList= textClassifier.classify("赠人玫瑰，手有余香");

        System.out.println(personalityTraitList);
    }
}
