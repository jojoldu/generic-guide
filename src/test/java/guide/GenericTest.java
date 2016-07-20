package guide;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by jojoldu@gmail.com on 2016-07-20.
 */
public class GenericTest {

    private ObjectMapper objectMapper;
    private ResourcePatternResolver resourcePatternResolver;
    private String requestUrl = "classpath:book.json";

    @Before
    public void setup() throws Exception {
        objectMapper = new ObjectMapper();
        resourcePatternResolver = new PathMatchingResourcePatternResolver();
    }

    private <T> DataResponse<T> request(String requestUrl, Class<T> clazz) throws Exception {
        Resource resource = resourcePatternResolver.getResource(requestUrl);
        return objectMapper.readValue(IOUtils.toString(resource.getInputStream(), "UTF-8"), new TypeReference<DataResponse<T>>() { });
    }

    @Test
    public void test_origin () throws Exception {
        DataResponse<Book> result = request(requestUrl, Book.class);
        assertThat(result.getData().get(0).getAuthor(), is("이일민"));
    }

    private <T> DataResponse<T> requestSolve(String requestUrl, TypeReference<DataResponse<T>> type) throws Exception {
        Resource resource = resourcePatternResolver.getResource(requestUrl);
        return objectMapper.readValue(IOUtils.toString(resource.getInputStream(), "UTF-8"), type);
    }

    @Test
    public void test_solve () throws Exception {
        DataResponse<Book> result = requestSolve(requestUrl, new TypeReference<DataResponse<Book>>(){});
        assertThat(result.getData().get(0).getAuthor(), is("이일민"));
    }
}
