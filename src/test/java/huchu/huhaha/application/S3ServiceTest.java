package huchu.huhaha.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.amazonaws.services.s3.AmazonS3;
import java.io.IOException;
import java.net.URL;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@Transactional
@SpringBootTest
class S3ServiceTest {

    @MockBean
    private AmazonS3 amazonS3;

    @Mock
    private MultipartFile multipartFile;

    @Autowired
    private S3Service s3Service;

    @Test
    void 파일을_업로드하고_URL을_반환한다() throws IOException {
        // given
        String name = "testImage";

        given(amazonS3.getUrl(anyString(), anyString()))
                .willReturn(new URL("https://s3/" + name));

        // when
        String imageUrl = s3Service.upload(name, multipartFile);

        // then
        assertThat(imageUrl).isEqualTo("https://test.cloudfront/testImage");

        then(amazonS3)
                .should(times(1))
                .putObject(anyString(), anyString(), any(), any());
    }
}
