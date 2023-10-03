package huchu.huhaha.dto;

import huchu.huhaha.domain.Image;

public record ImageResponse(String title, String url) {

    public static ImageResponse from(Image image) {
        return new ImageResponse(image.imageName(), image.imageUrl());
    }
}
