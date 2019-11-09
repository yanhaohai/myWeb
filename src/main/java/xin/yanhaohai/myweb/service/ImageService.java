package xin.yanhaohai.myweb.service;

import xin.yanhaohai.myweb.domain.Image;

import java.util.List;

public interface ImageService {
    List<Image> getAllImages();
    void saveImage(Image image);
}
