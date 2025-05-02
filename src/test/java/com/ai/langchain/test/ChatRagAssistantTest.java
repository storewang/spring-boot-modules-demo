package com.ai.langchain.test;

import com.ai.langchain.service.ChatRagAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 石头
 * @version 1.0
 * @date 2025/5/2
 **/
@SpringBootTest
public class ChatRagAssistantTest {
    @Autowired
    private ChatRagAssistant chatRagAssistant;
    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;
    @Autowired
    private OpenAiEmbeddingModel embeddingModel;

    @Test
    void testAddDocument() {
        Document document = FileSystemDocumentLoader.loadDocument("/home/yoyoo/test/test001.pdf",new ApachePdfBoxDocumentParser());
        EmbeddingStoreIngestor build = EmbeddingStoreIngestor.builder().embeddingModel(embeddingModel).embeddingStore(embeddingStore).build();

        build.ingest(document);
    }

    @Test
    void testChatWithRag(){
        String chat = chatRagAssistant.chat("王妍玥诊察费是多少");
        System.out.println(chat);

        chat = chatRagAssistant.chat("王妍玥的门诊号是多少");
        System.out.println(chat);
    }
}


