package huchu.star.application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public class ImageMultipartFile implements MultipartFile {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final String originalFilename;


    public ImageMultipartFile(ByteArrayOutputStream byteArrayOutputStream, String originalFilename) {
        this.byteArrayOutputStream = byteArrayOutputStream;
        this.originalFilename = originalFilename;
    }

    @Override
    public String getName() {
        return originalFilename.split("\\.")[0];
    }

    @Override
    public String getOriginalFilename() {
        return originalFilename;
    }

    @Override
    public String getContentType() {
        return originalFilename.split("\\.")[1];
    }

    @Override
    public boolean isEmpty() {
        return byteArrayOutputStream.size() == 0;
    }

    @Override
    public long getSize() {
        return byteArrayOutputStream.size();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        //no-op
    }
}
