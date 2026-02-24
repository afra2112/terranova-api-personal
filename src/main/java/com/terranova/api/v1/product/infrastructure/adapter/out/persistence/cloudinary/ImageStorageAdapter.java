package com.terranova.api.v1.product.infrastructure.adapter.out.persistence.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.terranova.api.v1.product.domain.port.out.ImageStoragePort;
import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CloudinaryResponse;
import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import com.terranova.api.v1.shared.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class ImageStorageAdapter implements ImageStoragePort {

    private final Cloudinary cloudinary;

    public ImageStorageAdapter(@Value("${cloudinary.url}") String url) {
        this.cloudinary = new Cloudinary(url);
    }

    @Override
    public CloudinaryResponse uploadToCloudinary(byte[] contentStream, String filename) {
        try {
            Map response = cloudinary.uploader().upload(contentStream, ObjectUtils.asMap("public_id", filename));

            return new CloudinaryResponse(response.get("secure_url").toString(), response.get("format").toString());
        } catch (IOException e){
            throw new BusinessException(ErrorCodeEnum.CLOUDINARY_ERROR, e.getMessage());
        }
    }

    @Override
    public void deleteImages(String fileName) {
        try {
            cloudinary.uploader().destroy(fileName, ObjectUtils.emptyMap());
        }catch (IOException e){
            throw new BusinessException(ErrorCodeEnum.CLOUDINARY_ERROR, e.getMessage());
        }
    }
}