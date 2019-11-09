package xin.yanhaohai.myweb.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.yanhaohai.myweb.dao.UserDao;
import xin.yanhaohai.myweb.domain.Image;
import xin.yanhaohai.myweb.service.ImageService;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<Image> getAllImages() {
        List<Image> allImages = userDao.getAllImages();
        return allImages;

    }

    @Override
    public void saveImage(Image image) {
        userDao.saveImage(image);
    }
}
