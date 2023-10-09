package huchu.post.presentation;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import com.amazonaws.services.s3.AmazonS3;
import huchu.post.domain.Post;
import huchu.post.domain.PostRepository;
import huchu.post.dto.PostResponse;
import huchu.post.dto.PostSaveRequest;
import huchu.post.dto.PostSaveResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Sql(value = "/truncate.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PostRepository postRepository;

    @MockBean
    private AmazonS3 amazonS3;

    private ClassPathResource testResource;

    @BeforeEach
    public void setUp() throws IOException {
        RestAssured.port = port;
        testResource = new ClassPathResource("testImage.png");

        given(amazonS3.getUrl(anyString(), anyString()))
                .willReturn(new URL("https://s3/testImage"));
    }

    @Test
    void 이미지를_업로드_한다() throws IOException {
        // given
        PostSaveRequest request = new PostSaveRequest("testImage");
        File file = testResource.getFile();

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .multiPart("file", file, IMAGE_JPEG_VALUE)
                .multiPart("dto", request, APPLICATION_JSON_VALUE)
                .when().post("/images")
                .then().log().all()
                .extract();

        // then
        PostSaveResponse postSaveResponse = response.as(PostSaveResponse.class);

        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(CREATED.value());
            softly.assertThat(postSaveResponse.imageId()).isNotNull();
        });
    }

    @Test
    void 모든_이미지를_조회한다() {
        // given
        postRepository.save(new Post("test", "test.url"));

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/images")
                .then().log().all()
                .extract();

        // then
        List<PostResponse> postRespons = response.as(new TypeRef<>() {
        });

        assertSoftly(softly -> {
            softly.assertThat(response.statusCode()).isEqualTo(OK.value());
            softly.assertThat(postRespons).containsExactly(new PostResponse("test", "test.url"));
        });
    }
}
